package com.sps.routes

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.*
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.{ActorMaterializer, Materializer}

import play.api.libs.json.*
import play.api.libs.functional.syntax.*

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

import com.sps.db.ConnectionInfo
import com.sps.db.dao.LotDao
import com.sps.config.Config
import com.sps.server.Session

class Parking(implicit config: Config) extends com.sps.server.Route {
  val lotDao = new LotDao(ConnectionInfo.fromConfig(config))

  val route = pathPrefix("parking") {
    path(IntNumber) { id =>
      concat(
        extractRequest { request =>
          put {
            headerValueByName(Session.HTTP_HEADER_NAME) { session_id =>
              if (Session.getSession(session_id).isEmpty) {
                complete(401, HttpEntity(ContentTypes.`application/json`, s"""{"error": "Invalid session"}"""))
              } else {
                implicit val system = actorSystem
                implicit val ec = executionContext
                onComplete(Unmarshal(request.entity).to[String]) {
                  case Success(body) => {
                    try {
                      val json = Json.parse(body)
                      val lots = lotDao.getBy("internal_id", s"$id")
                      if (lots.length > 1) {
                        complete(400, HttpEntity(ContentTypes.`application/json`, s"""{"error": "There's more than 1 lot with the same id. Please report this issue"}"""))
                      } else if (lots.isEmpty) {
                        complete(400, HttpEntity(ContentTypes.`application/json`, s"""{"error": "There's no lots with such id. Please report this issue"}"""))
                      } else {
                        lots(0).status = (json \ "status").get.as[Int]
                        lotDao.update(lots(0))
                        complete(HttpEntity(ContentTypes.`application/json`, "{}"))
                      }
                    } catch {
                      case ex => complete(500, HttpEntity(ContentTypes.`application/json`, s"""{"error": "${ex.getMessage}"}"""))
                    }
                  }
                  case Failure(ex) => complete(500, HttpEntity(ContentTypes.`application/json`, s"""{"error": "${ex.getMessage}"}"""))
                }
              }
            }
          }
        },
        get {
          headerValueByName(Session.HTTP_HEADER_NAME) { session_id =>
            if (Session.getSession(session_id).isEmpty) {
              complete(401, HttpEntity(ContentTypes.`application/json`, s"""{"error": "Invalid session"}"""))
            } else {
              try {
                val lots = lotDao.getBy("internal_id", s"$id")
                if (lots.length > 1) {
                  complete(400, HttpEntity(ContentTypes.`application/json`, s"""{"error": "There's more than 1 lot with the same id. Please report this issue"}"""))
                } else if (lots.isEmpty) {
                  complete(400, HttpEntity(ContentTypes.`application/json`, s"""{"error": "There's no lots with such id. Please report this issue"}"""))
                } else {
                  complete(HttpEntity(ContentTypes.`application/json`, s"""{"id": ${lots(0).internal_id}, "status": ${lots(0).status}}"""))
                }
              } catch {
                case ex => complete(500, HttpEntity(ContentTypes.`application/json`, s"""{"error": "${ex.getMessage}"}"""))
              }
            }
          }
        }
      )
    }
  }
}
