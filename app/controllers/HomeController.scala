package controllers

import javax.inject._

import play.api.mvc._
import services.AuthAction
import dao.{UserDAO, CategoryDAO, PaymentSourceDAO, PaymentDestinationDAO, AccountDAO}
import play.api.libs.concurrent.Execution.Implicits._

@Singleton
class HomeController @Inject() (val userDAO: UserDAO,
                                val categoryDAO: CategoryDAO,
                                val paymentSourceDAO: PaymentSourceDAO,
                                val paymentDestinationDAO: PaymentDestinationDAO,
                                val accountDAO: AccountDAO) extends Controller {
  val message = "私の新しいアプリケーション"
  def index = AuthAction {
    Ok(views.html.index(message))
  }

  def users = Action.async { implicit request =>
    userDAO.list map { u =>
      Ok(views.html.users(u))
    }
  }

  def categories = Action.async { implicit request =>
    categoryDAO.list map { c =>
      Ok(views.html.categories(c))
    }
  }

  def paymentSources = Action.async { implicit request =>
    paymentSourceDAO.list map { p =>
      Ok(views.html.paymentSources(p))
    }
  }

  def paymentDestinations = Action.async { implicit request =>
    paymentDestinationDAO.list.map { p =>
      Ok(views.html.paymentDestination(p))
    }
  }

  def accounts = Action.async { implicit request =>
    accountDAO.list.map { p =>
      Ok(views.html.account(p))
    }
  }
}
