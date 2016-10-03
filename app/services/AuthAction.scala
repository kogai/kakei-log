package services

import scala.concurrent.Future
import play.api.Logger
import play.api.mvc.{ActionBuilder, Request, Result}
import play.api.mvc.Results.Redirect

import controllers.routes
import values.session

object AuthAction extends ActionBuilder[Request] {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = {
    request.session.get(session.SESSION_KEY) match {
      case Some(userId) =>
        Logger.info("user-id is: " + userId)
        block(request)
      case None =>
        Logger.info("redirect to login page.")
        Future.successful(Redirect(routes.LoginController.index()))
    }
  }
}
