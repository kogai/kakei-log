package dao

import javax.inject.Inject
import java.sql.Date

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import models.{Account, UserModel}

import scala.concurrent.Future

class AccountDAO @Inject() (protected val databaseConfigProvider: DatabaseConfigProvider){
  private class AccountTable(tag: Tag) extends Table[Account] (tag, "Account"){
    def account_id = column[Long]("account_id", O.PrimaryKey, O.AutoInc)
    def user_id = column[Long]("user_id")
    def name = column[String]("name")
    def cost = column[Long]("cost")
    def category = column[Int]("category")
    def register_at = column[Date]("register_at")
    def payment_source_id = column[Int]("payment_source_id")
    def payment_destination_id = column[Int]("payment_destination_id")

    override def * = (
      account_id,
      user_id,
      name,
      cost,
      category,
      register_at,
      payment_source_id,
      payment_destination_id) <> (Account.tupled, Account.unapply)
  }

  val dbConfig = databaseConfigProvider.get[JdbcProfile]
  private val accounts = TableQuery[AccountTable]

  def list: Future[Seq[Account]] = {
    dbConfig.db.run(accounts.result)
  }
}
