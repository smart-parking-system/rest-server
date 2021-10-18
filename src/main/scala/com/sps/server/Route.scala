package com.sps.server

import akka.actor.typed.ActorSystem
import scala.concurrent.ExecutionContext
import com.sps.config.Config

trait Route {
  var actorSystem: ActorSystem[Any] = null
  var executionContext: ExecutionContext = null
  val route: akka.http.scaladsl.server.Route
}
