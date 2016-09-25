package controllers

import javax.inject._

import play.api.Logger
import play.api.mvc._
import play.api.i18n._
import play.api.data.Form
import play.api.data.Forms._
import models._

@Singleton
class LoginController @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {
  var userForm = Form {
    mapping(
      "email" -> email,
      "password" -> text
    )(UserModel.apply)(UserModel.unapply)
  }

  def index = Action {
    Ok(views.html.login("ログインページ"))
  }

  def login = Action { implicit request =>
    val sessionVar = "userInfo"
    val user = userForm.bindFromRequest.get
    print(user.email, user.password)
    if (user.email == "test@test.com" && user.password == "test") {
      Ok(views.html.index("ログインしました"))
        .withSession(sessionVar -> "user-id")
    } else {
      Unauthorized(views.html.error("ログインに失敗しました"))
    }
  }

  def logout = Action { implicit request =>
    Ok(views.html.index("ログアウトしました")).withNewSession
  }
}
