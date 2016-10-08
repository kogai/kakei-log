import org.scalatestplus.play._

class IntegrationSpec extends PlaySpec with OneServerPerTest with OneBrowserPerTest with HtmlUnitFactory {
  "Application" should {
    "ログインページへリダイレクトされる" in {
      go to ("http://localhost:" + port)
      pageSource must include ("ログインページ")
    }
  }
}
