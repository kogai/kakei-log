package controllers

import javax.inject._

import play.api.mvc._
import play.api.i18n._
import play.api.libs.concurrent.Execution.Implicits._
import models.{UserForm, Users}

@Singleton
class LoginController @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def index = Action {
    Ok(views.html.login(UserForm.form))
  }

  def login = Action { implicit request =>
    val sessionVar = "userInfo"
    UserForm.form.bindFromRequest.fold(
      e => {
        BadRequest(views.html.login(e))
      },
      user => {
        print(user.email, user.password)
        if (user.email == "test@test.com" && user.password == "test") {
          Ok(views.html.index("ログインしました"))
            .withSession(sessionVar -> "user-id")
        } else {
          Unauthorized(views.html.error("ログインに失敗しました"))
        }
      }
    )
  }

  def logout = Action { implicit request =>
    Ok(views.html.index("ログアウトしました")).withNewSession
  }

  def list = Action.async { implicit request =>
    Users.listAll map { users =>
      println(users)
      Ok(views.html.index("ユーザのリスト"))
    }
  }
}
