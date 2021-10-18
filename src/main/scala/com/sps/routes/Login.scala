package com.sps.routes

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.*
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.directives.Credentials
import com.sps.server.Session
import com.sps.config.{Config, User}

class Login(implicit config: Config) extends com.sps.server.Route {

  val route =
    concat(
      path("session_status" / JavaUUID) { uuid =>
        get {
          Session.getSession(uuid.toString) match {
            case Some(sesion) => complete(HttpEntity(ContentTypes.`application/json`, s"""{"session": {"uuid": "${uuid.toString}","valid": true}}"""))
            case None => complete(500, HttpEntity(ContentTypes.`application/json`, s"""{"session": {"uuid": "${uuid.toString}","valid": false}, "error": "Invalid Session"}"""))
          }
        }
      },
      path("login") {
        authenticateBasic(realm = "login", authenticator) { userName =>
          get {
            val session = Session.newSession()
            complete(HttpEntity(ContentTypes.`application/json`, s"""{"session": {"uuid": "${session.uuid.toString}","valid": true}}"""))
          }
        }
      }
    )

  def authenticator(credentials: Credentials): Option[String] = credentials match {
    case p @ Credentials.Provided(id) =>
      config.users.find(u => u.name == p.identifier) match {
        case Some(user) => if p.verify(user.pass) then Some(user.name) else None
        case None => None
      }
    case _ => None
  }

}
