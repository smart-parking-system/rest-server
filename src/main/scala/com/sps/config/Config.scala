package com.sps.config

import play.api.libs.json._
import play.api.libs.functional.syntax._
import com.sps.config.User

case class Config(var ip: String = "localhost",
                  var port: Int = 8080,
                  var users: List[User] = List.empty[User])

object Config {
  val ROOT_USER_NAME: String = "root"

  def fromFile(fileName: String): Config = {
    implicit val userReads: Reads[User] = (
        (JsPath \\ "name").read[String] and
        (JsPath \\ "pass").read[String]
      )(User.apply _)

    try {
      val fileContents = scala.io.Source.fromFile(fileName).mkString
      val json = Json.parse(fileContents)
      Config(
        (json \ "ip").get.as[String],
        (json \ "port").get.as[Int],
        (json \ "users").get.as[List[User]]
      )
    } catch {
      case _ => Config()
    }
  }
}
