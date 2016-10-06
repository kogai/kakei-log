package dao

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import models.PaymentSource

import scala.concurrent.Future

class PaymentSourceDAO @Inject() (protected val databaseConfigProvider: DatabaseConfigProvider){
  private class PaymentSourceTable(tag: Tag) extends Table[PaymentSource] (tag, "PaymentSource"){
    def payment_source_id = column[Long]("payment_source_id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def user_id = column[Option[Long]]("user_id")
    def amount = column[Long]("amount")

    override def * = (
      payment_source_id,
      name,
      user_id,
      amount) <> (PaymentSource.tupled, PaymentSource.unapply)
  }

  val dbConfig = databaseConfigProvider.get[JdbcProfile]
  private val paymentSource = TableQuery[PaymentSourceTable]

  def list: Future[Seq[PaymentSource]] = {
    dbConfig.db.run(paymentSource.result)
  }
}
