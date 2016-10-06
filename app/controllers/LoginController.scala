package controllers

import javax.inject.{Singleton, Inject}
import scala.concurrent.Future
import play.api.mvc._
import play.api.i18n._
import play.api.libs.concurrent.Execution.Implicits._

import models.UserForm
import dao.UserDAO
import values.session

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
          case Some(u) => Redirect(routes.HomeController.index)
            .withSession(session.SESSION_KEY -> u.id.toString)
          case None => Unauthorized(views.html.error("メールアドレスかパスワードが間違っています"))
        }
      }
    )
  }

  def logout = Action { implicit request =>
    Ok(views.html.index("ログアウトしました")).withNewSession
  }
}
