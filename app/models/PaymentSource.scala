package models

case class PaymentSource (
                      payment_source_id: Long,
                      name: String,
                      user_id: Option[Long],
                      amount: Long
                    )
