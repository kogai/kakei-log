package controllers

import javax.inject._
import play.api.mvc._
import services.AuthAction

@Singleton
class HomeController @Inject() extends Controller {
  val message = "私の新しいアプリケーション"
  def index = AuthAction {
    Ok(views.html.index(message))
  }
}
