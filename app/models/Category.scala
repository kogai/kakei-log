package models

case class Category (category_id: Long,
                     name: String,
                     hierarchy: Long,
                     user_id: Option[Long],
                     parent_id: Option[Long])

case class CategoryTree(category_id: Long,
                        name: String,
                        children: Seq[CategoryTree])

object CategoryTree {
  def foldCategoryTree(categories: Seq[Category]) = {
    val seed: Seq[CategoryTree] = Seq()
    categories.foldLeft[Seq[CategoryTree]](seed) {(acc, next) =>
      next.parent_id match {
        case Some(parent_id) => acc
          .map { parent =>
            if (parent.category_id == parent_id) {
              val el = new CategoryTree(next.category_id, next.name, Seq())
              println(parent.category_id, parent_id, parent, el)
              parent.children :+ el
              parent
            } else { parent }
          }
        case None => acc ++ Seq(new CategoryTree(next.category_id, next.name, Seq()))
      }
    }
  }
}
