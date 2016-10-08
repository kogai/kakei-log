import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends PlaySpec with OneAppPerTest {
  "Routes" should {
    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }
  }

  "HomeController" should {
    "ユーザリストページを描画できる" in {
      val userList = route(app, FakeRequest(GET, "/list/user")).get

      status(userList) mustBe OK
      contentType(userList) mustBe Some("text/html")
      contentAsString(userList) must include ("test@test.com")
      contentAsString(userList) must include ("yet-register@test.com")
    }

  }

//  "CountController" should {
//    "return an increasing count" in {
//      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "0"
//      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "1"
//      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "2"
//    }
//  }

}
