package models
import java.sql.Date

case class Account (id: Long,
                    user_id: Long,
                    name: String,
                    cost: Long,
                    category: Int,
                    register_at: Date,
                    payment_source: Int,
                    payment_destination: Int)
