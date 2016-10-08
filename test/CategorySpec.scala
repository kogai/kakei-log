import org.scalatestplus.play._
import models.Category
import models.CategoryTree.foldCategoryTree

class CategorySpec extends PlaySpec {
  "Models.Category" should {
    "Categoryテーブルからカテゴリーツリーを作れる" in {
      val mock_1 = Category(category_id = 1, name = "食費", hierarchy = 0, user_id = None, parent_id = None)
      val mock_2 = Category(category_id = 2, name = "肉類", hierarchy = 1, user_id = None, parent_id = Some(1))
      val mock_3 = Category(category_id = 3, name = "魚介類", hierarchy = 1, user_id = None, parent_id = Some(1))
      val mock_4 = Category(category_id = 4, name = "野菜", hierarchy = 1, user_id = None, parent_id = Some(1))
      val mock_5 = Category(category_id = 5, name = "乳製品", hierarchy = 1, user_id = None, parent_id = Some(1))
      val mock_6 = Category(category_id = 6, name = "加工食品", hierarchy = 1, user_id = None, parent_id = Some(1))
      val mock_7 = Category(category_id = 7, name = "調味料", hierarchy = 1, user_id = None, parent_id = Some(1))
      val mock_8 = Category(category_id = 8, name = "日用品", hierarchy = 0, user_id = None, parent_id = None)
      val mock_9 = Category(category_id = 9, name = "清掃用品", hierarchy = 1, user_id = None, parent_id = Some(8))
      val mocks = Seq(mock_1, mock_2, mock_3, mock_4, mock_5, mock_6, mock_7, mock_8, mock_9)

//      mocks.foreach(println)
      val expected = foldCategoryTree(mocks)
//      expected.foreach(println)

      assert(expected.size == 2)
      assert(expected(1).children.size == 6)
      assert(expected(2).children.size == 1)
    }
  }
}
