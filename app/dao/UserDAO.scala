package dao

import java.util.UUID.randomUUID
import play.api.Logger
import play.api.libs.mailer._
import scala.concurrent.Future
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import com.github.t3hnar.bcrypt._
import play.api.libs.concurrent.Execution.Implicits._

import models.UserModel

class UserDAO @Inject() (protected val databaseConfigProvider: DatabaseConfigProvider, mailerClient: MailerClient){
  private class UserTable(tag: Tag) extends Table[UserModel](tag, "users") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def email = column[String]("mail_address")
    def password = column[String]("password_digest")
    def verifyId = column[String]("verifiy_id")
    def isVerified = column[Boolean]("is_verified", O.Default(false))

    override def * = (id, email, password, verifyId, isVerified) <> (UserModel.tupled, UserModel.unapply)
  }

  val dbConfig = databaseConfigProvider.get[JdbcProfile]
  val SESSION_KEY = "SESSION_KEY"
  private val users = TableQuery[UserTable]

  val digest = (raw: String) => raw.bcrypt
  val isMatch = (candidate: String, expect: String) => candidate.isBcrypted(expect)

  def get(id: Long): Future[Option[UserModel]] = {
    dbConfig.db.run(users.filter(_.id === id).result.headOption)
  }

  def add(email: String, rawPassword: String): Future[Boolean] = {
    val uuid = randomUUID.toString
    val user = UserModel(0, email, digest(rawPassword), verifyId = uuid, isVerified = false)
    println(user)
//    send(uuid)
    dbConfig.db.run(users += user)
      .map(_ => true)
      .recover {
        case ex: Exception =>
          Logger.info(ex.getCause.getMessage)
          false
      }
  }

  def send(uuid: String): Unit = {
    val mail = Email(
      subject = "タイトル",
      from = "運営 FROM <kogai0121@gmail.com>",
      to = Seq("テスト TO <kogai0121@gmail.com>"),
      bodyText = Some("本文メッセージ"),
      bodyHtml = Some(
        s"""
          |本文メッセージ<br />
          |<a href="http://localhost:9000/verify/$uuid">認証ページ</a>
        """.stripMargin)
    )
    println(mail)
    mailerClient.send(mail)
  }

  def auth(email: String, rawPassword: String): Future[Option[UserModel]] = {
    dbConfig.db.run(users.filter(_.email === email).result.headOption)
      .map {
        case Some(u) if isMatch(rawPassword, u.password) => Some(u)
        case Some(u) => None
        case None => None
      }
      .recover {
        case ex: IllegalArgumentException =>
          Logger.error(ex.getMessage)
          None
      }
  }

  def verify(verifyId: String): Future[Boolean] = {
    dbConfig.db.run(users.filter(_.verifyId === verifyId).map(_.isVerified).update(true))
      .map(_ == 1)
  }

  def listAll: Future[Seq[UserModel]] = {
    dbConfig.db.run(users.result)
  }
}
