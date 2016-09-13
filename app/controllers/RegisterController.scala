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
      "email" -> text,
      "password" -> text
    )(UserModel.apply)(UserModel.unapply)
  }

  def index = Action {
    Ok(views.html.register(userForm))
  }

  def create = Action { implicit request =>
    val user = userForm.bindFromRequest.get
    println(user)
    Ok(views.html.index("ok"))
  }
}
