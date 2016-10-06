package controllers

import javax.inject._

import play.api.mvc._
import services.AuthAction
import dao.{UserDAO, CategoryDAO, PaymentSourceDAO}
import play.api.libs.concurrent.Execution.Implicits._

@Singleton
class HomeController @Inject() (val user: UserDAO,
                                val category: CategoryDAO,
                                val paymentSource: PaymentSourceDAO) extends Controller {
  val message = "私の新しいアプリケーション"
  def index = AuthAction {
    Ok(views.html.index(message))
  }

  def users = Action.async { implicit request =>
    user.list map { u =>
      Ok(views.html.users(u))
    }
  }

  def categories = Action.async { implicit request =>
    category.list map { c =>
      Ok(views.html.categories(c))
    }
  }

  def paymentSources = Action.async { implicit request =>
    paymentSource.list map { p =>
      Ok(views.html.paymentSources(p))
    }
  }
}
