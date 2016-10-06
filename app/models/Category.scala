package models

case class Category (
                      category_id: Long,
                      name: String,
                      hierarchy: Long,
                      user_id: Option[Long],
                      parent_id: Option[Long]
                    )
