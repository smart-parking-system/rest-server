package com.sps.config

import play.api.libs.json._
import play.api.libs.functional.syntax._
import com.sps.config.User
import com.sps.security.Encryption

case class Config(var users: List[User] = List.empty[User],
                  var encryption: Encryption = Encryption("", ""))

object Config {
  val ROOT_USER_NAME: String = "root"
  private var config = Config()

  def get(): Config = config
  def set(newConfig: Config): Unit = config = newConfig

  def fromFile(fileName: String): Config = {
    implicit val userReads: Reads[User] = (
        (JsPath \\ "name").read[String] and
        (JsPath \\ "pass").read[String]
      )(User.apply _)

    try {
      val fileContents = scala.io.Source.fromFile(fileName).mkString
      val json = Json.parse(fileContents)
      Config(
        (json \ "users").get.as[List[User]],
        Encryption((json \ "security" \ "key").get.as[String], (json \ "security" \ "iv").get.as[String])
      )
    } catch { // TODO print error
      case _ => Config()
    }
  }
}
