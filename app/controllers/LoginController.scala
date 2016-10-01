package controllers

import javax.inject.{Singleton, Inject}
import scala.concurrent.Future
import play.api.mvc._
import play.api.i18n._
import play.api.libs.concurrent.Execution.Implicits._
import models.{UserForm, Users}

@Singleton
class LoginController @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def index = Action {
    Ok(views.html.login(UserForm.form))
  }

  def login = Action.async { implicit request =>
    val sessionVar = "userInfo"
    UserForm.form.bindFromRequest.fold(
      e => Future.successful(BadRequest(views.html.login(e))),
      user => {
        Users.auth(user.email, user.password).map {
          case Some(u) => Ok(views.html.index("ログインしました"))
            .withSession(sessionVar -> u.id.toString)
          case None => Unauthorized(views.html.error("ログインに失敗しました"))
        }
      }
    )
  }

  def logout = Action { implicit request =>
    Ok(views.html.index("ログアウトしました")).withNewSession
  }

  def list = Action.async { implicit request =>
    Users.listAll map { users =>
      Ok(views.html.users(users))
    }
  }
}
