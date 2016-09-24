package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n._
import play.api.data.Form
import play.api.data.Forms._
import models._

@Singleton
class RegisterController @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {
  var userForm = Form {
    mapping(
      "email" -> email,
      "password" -> text
    )(UserModel.apply)(UserModel.unapply)
  }

  def index = Action {
    Ok(views.html.register("アカウント登録ページ"))
  }

  def create = Action { implicit request =>
    val user = userForm.bindFromRequest.get
    println(user.email, user.password)
    Ok(views.html.index("ok"))
  }
}
