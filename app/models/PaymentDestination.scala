package models

case class PaymentDestination(payment_destination_id: Long,
                              name: String,
                              user_id: Option[Long])
