package com.sps

import scala.util.control.Breaks._

import com.sps.config.Config
import com.sps.server.Server
import com.sps.routes.{Parking, Login}

object App {
  def main(args: Array[String]): Unit = {
    if (args.contains("-h") || args.contains("--help")) {
      println(s"SPS Rest Server v${Server.VERSION}\nAll rights reserved\nUsage: rest-server.jar [OPTIONS]\nOptions:")
      println(s"  -h,--help - Help\n  -c FILE,--config FILE - Config file path")
      return
    }

    implicit var config = Config()
    var index = -1

    if (args.contains("-c")) {
      index = args.indexOf("-c") + 1
    } else if (args.contains("--config")) {
      index = args.indexOf("--config") + 1
    }

    if (index != -1) {
      println(s"Config file: ${args(index)}")
      config = Config.fromFile(args(index))
    }

    val server = new Server(List(new Parking, new Login))
    server.run()
  }
}
