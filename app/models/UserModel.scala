package models

import play.api.Play
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import com.github.t3hnar.bcrypt._
import play.api.libs.concurrent.Execution.Implicits._


case class UserModel (email: String, password: String)

object UserForm {
  var form = Form {
    mapping(
      "email" -> email,
      "password" -> text.verifying("4~32文字以上", { p => p.length() >= 4 && p.length <= 32 })
    )(UserModel.apply)(UserModel.unapply)
  }
}

case class UserModel2 (id: Int, email: String, password: String)
class UserTableDef(tag: Tag) extends Table[UserModel2](tag, "User") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def email = column[String]("mail_address")
  def password = column[String]("password_digest")

  override def * =
    (id, email, password) <>(UserModel2.tupled, UserModel2.unapply)
}

object Users {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val users = TableQuery[UserTableDef]

//  def add(user: User): Future[String] = {
//    dbConfig.db.run(users += user).map(res => "User successfully added").recover {
//      case ex: Exception => ex.getCause.getMessage
//    }
//  }
//
//  def delete(id: Long): Future[Int] = {
//    dbConfig.db.run(users.filter(_.id === id).delete)
//  }

//  def get(id: Long): Future[Option[UserModel2]] = {
//    dbConfig.db.run(users.filter(_.id === id).result.headOption)
//  }
  def get(id: Int): Future[Option[UserModel2]] = {
    dbConfig.db.run(users.filter(_.id === id).result.headOption)
  }

  def auth(email: String, rawPassword: String): Future[Option[UserModel2]] = {
    dbConfig.db.run(users.filter(_.email === email).result.headOption)
      .map {
        case Some(u) if isMatch(rawPassword, u.password) => Some(u)
        case Some(u) => None
        case None => None
      }
  }

  private def isMatch(candidate: String, digest: String): Boolean = {
    candidate.isBcrypted(digest)
  }

  def digest(rawPassword: String): String = {
    rawPassword.bcrypt
  }

  def listAll: Future[Seq[UserModel2]] = {
    dbConfig.db.run(users.result)
  }
}
