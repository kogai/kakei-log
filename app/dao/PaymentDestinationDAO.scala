package dao

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import models.PaymentDestination

import scala.concurrent.Future

class PaymentDestinationDAO @Inject() (protected val databaseConfigProvider: DatabaseConfigProvider){
  private class PaymentDestinationTable(tag: Tag) extends Table[PaymentDestination] (tag, "PaymentDestination"){
    def payment_destination_id = column[Long]("payment_destination_id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def user_id = column[Option[Long]]("user_id")

    override def * = (
      payment_destination_id,
      name,
      user_id) <> (PaymentDestination.tupled, PaymentDestination.unapply)
  }

  val dbConfig = databaseConfigProvider.get[JdbcProfile]
  private val paymentDestination = TableQuery[PaymentDestinationTable]

  def list: Future[Seq[PaymentDestination]] = {
    dbConfig.db.run(paymentDestination.result)
  }
}
