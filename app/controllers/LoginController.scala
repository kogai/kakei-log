package controllers

import javax.inject.{Singleton, Inject}
import scala.concurrent.Future
import play.api.mvc._
import play.api.i18n._
import play.api.libs.concurrent.Execution.Implicits._

import models.UserForm
import dao.UserDAO

@Singleton
class LoginController @Inject() (val messagesApi: MessagesApi, val user: UserDAO) extends Controller with I18nSupport {
  def index = Action {
    Ok(views.html.login(UserForm.form))
  }

  def login = Action.async { implicit request =>
    UserForm.form.bindFromRequest.fold(
      e => Future.successful(BadRequest(views.html.login(e))),
      input => {
        user.auth(input.email, input.password).map {
          case Some(u) => Ok(views.html.index("ログインしました"))
            .withSession(user.SESSION_KEY -> u.id.toString)
          case None => Unauthorized(views.html.error("ログインに失敗しました"))
        }
      }
    )
  }

  def logout = Action { implicit request =>
    Ok(views.html.index("ログアウトしました")).withNewSession
  }

  def list = Action.async { implicit request =>
    user.listAll map { users =>
      Ok(views.html.users(users))
    }
  }
}
