package controllers

import javax.inject._

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
      "password" -> text.verifying("4~32文字以上", { p => p.length() >= 4 && p.length <= 32 })
    )(UserModel.apply)(UserModel.unapply)
  }

  def index = Action {
    Ok(views.html.login(userForm))
  }

  def login = Action { implicit request =>
    val sessionVar = "userInfo"
    userForm.bindFromRequest.fold(
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
}
