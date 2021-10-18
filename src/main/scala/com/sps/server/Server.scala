package com.sps.server

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.*
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.server.Route

import scala.io.StdIn
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

import com.sps.config.Config
import com.sps.server.Logger
import com.sps.server.Session

sealed class Server(routeObjects: List[com.sps.server.Route])(implicit config: Config) {

  def run(): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "sps-system")
    implicit val executionContext = system.executionContext

    routeObjects.foreach(r => {r.executionContext = executionContext; r.actorSystem = system})

    val route = concat(routeObjects.map(r => pathPrefix("api") {r.route}):_*)

    val bindingFuture = Http().newServerAt(config.ip, config.port).bind(route)

//    system.scheduler.scheduleWithFixedDelay(1.seconds, 10.seconds)(() => Session.updateSessionState())

    Logger.logger.info(s"Server now online at ${config.ip}:${config.port}") // logger.info("Press RETURN to stop...")

    /* TODO Think of some kind of console instead of readLine */
    StdIn.readLine() // let it run until user presses return

    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }

}

object Server {
  val VERSION = "0.1"
}
