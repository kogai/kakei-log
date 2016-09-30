package controllers

import javax.inject.{Singleton, Inject}
import play.api.mvc._
import play.api.i18n._
import models.{UserForm, Users, UserModel}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class RegisterController @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def index = Action {
    Ok(views.html.register(UserForm.form))
  }

  def create = Action.async { implicit request =>
    val input = UserForm.form.bindFromRequest.get
    val newUser = UserModel(None, input.email, input.password)
    Users.add(newUser).map { implicit res =>
      println(res)
      Redirect(routes.HomeController.index())
    }
  }
}
