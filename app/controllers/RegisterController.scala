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
      "password" -> text.verifying("4~32文字以上", { p => p.length() >= 4 && p.length <= 32 })
    )(UserModel.apply)(UserModel.unapply)
  }

  def index = Action {
    Ok(views.html.register(userForm))
  }

  def create = Action { implicit request =>
    val user = userForm.bindFromRequest.get
    println(user.email, user.password)
    Ok(views.html.index("ok"))
  }
}
