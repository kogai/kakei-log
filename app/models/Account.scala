package models
import java.sql.Date

case class Account (account_id: Long,
                    user_id: Option[Long],
                    name: String,
                    amount: Long,
                    category_id: Long,
                    register_at: Date,
                    payment_source: Option[Long],
                    payment_destination: Option[Long])

