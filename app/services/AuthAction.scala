package services

import play.api.Logger
import play.api.mvc.{ActionBuilder, Request, Result}
import play.api.mvc.Results.Redirect
import scala.concurrent.Future
import controllers._

object AuthAction extends ActionBuilder[Request] {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = {
    val sessionVar = "userInfo"
    request.session.get(sessionVar) match {
      case Some(userId) => {
        Logger.info("user-id is: " + userId)
        block(request)
      }
      case None => {
        Logger.info("redirect to login page.")
        Future.successful(Redirect(routes.LoginController.index()))
      }
    }
  }
}
