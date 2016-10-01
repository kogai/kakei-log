package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc._
import play.api.i18n._
import models.{UserForm, UserModel, Users}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class RegisterController @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def index = Action {
    Ok(views.html.register(UserForm.form))
  }

  def create = Action.async { implicit request =>
    UserForm.form.bindFromRequest.fold(
      e => Future.successful(BadRequest(views.html.register(e))),
      input => {
        Users.add(input.email, input.password).map {
          case true => Redirect(routes.LoginController.index())
          case _ => BadRequest(views.html.error("アカウントの登録に失敗しました"))
        }
      }
    )
  }
}
