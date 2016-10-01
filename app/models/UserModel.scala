package models

import play.api.data.Form
import play.api.data.Forms._

case class UserFormModel (email: String, password: String)
object UserForm {
  var form = Form {
    mapping(
      "email" -> email,
      "password" -> text.verifying("パスワードは4~32文字以上", { p => p.length() >= 4 && p.length <= 32 })
    )(UserFormModel.apply)(UserFormModel.unapply)
  }
}

case class UserModel (id: Long, email: String, password: String)
