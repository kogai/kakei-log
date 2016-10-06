package dao

import javax.inject.Inject
import java.sql.Date

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import models.Category

import scala.concurrent.Future

class CategoryDAO @Inject() (protected val databaseConfigProvider: DatabaseConfigProvider){
  private class CategoryTable(tag: Tag) extends Table[Category] (tag, "Category"){
    def category_id = column[Long]("category_id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def hierarchy = column[Long]("hierarchy")
    def user_id = column[Option[Long]]("user_id")
    def parent_id = column[Option[Long]]("parent_id")

    override def * = (
      category_id,
      name,
      hierarchy,
      user_id,
      parent_id) <> (Category.tupled, Category.unapply)
  }

  val dbConfig = databaseConfigProvider.get[JdbcProfile]
  private val category = TableQuery[CategoryTable]

  def list: Future[Seq[Category]] = {
    dbConfig.db.run(category.result)
  }
}
