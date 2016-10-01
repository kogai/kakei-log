package dao

import play.api.Logger
import scala.concurrent.Future
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import com.github.t3hnar.bcrypt._
import play.api.libs.concurrent.Execution.Implicits._

import models.UserModel

class UserDAO @Inject() (protected val databaseConfigProvider: DatabaseConfigProvider){
  private class UserTable(tag: Tag) extends Table[UserModel](tag, "User") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def email = column[String]("mail_address")
    def password = column[String]("password_digest")

    override def * = (id, email, password) <> (UserModel.tupled, UserModel.unapply)
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
      val user = UserModel(0, email, digest(rawPassword))
    dbConfig.db.run(users += user)
      .map(_ => true)
      .recover {
        case ex: Exception =>
          Logger.info(ex.getCause.getMessage)
          false
      }
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

  def listAll: Future[Seq[UserModel]] = {
    dbConfig.db.run(users.result)
  }
}
