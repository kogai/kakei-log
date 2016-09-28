package models

import play.api.Play
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.Future
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

case class UserModel (email: String, password: String)

object UserForm {
  var form = Form {
    mapping(
      "email" -> email,
      "password" -> text.verifying("4~32文字以上", { p => p.length() >= 4 && p.length <= 32 })
    )(UserModel.apply)(UserModel.unapply)
  }
}

class UserTableDef(tag: Tag) extends Table[UserModel](tag, "User") {
//  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def email = column[String]("mail_address")
  def password = column[String]("password_digest")

  override def * =
//    (id, email, password) <>(UserModel.tupled, UserModel.unapply)
    (email, password) <>(UserModel.tupled, UserModel.unapply)
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

//  def get(id: Long): Future[Option[UserModel]] = {
//    dbConfig.db.run(users.filter(_.id === id).result.headOption)
//  }

  def listAll: Future[Seq[UserModel]] = {
    dbConfig.db.run(users.result)
  }
}
