package controllers

import javax.inject._
import play.api._
import play.api.mvc._
@Singleton
class RegisterController @Inject() extends Controller {
  val message = "私の新しいアプリケーション"
  def index = Action {
    Ok(views.html.register())
  }

}
