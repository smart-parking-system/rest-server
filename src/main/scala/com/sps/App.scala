package com.sps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.ComponentScan

import com.sps.config.Config
import com.sps.server.Server

@ComponentScan(Array("com.sps.controller", "com.sps.service", "com.sps.repository", "com.sps.data"))
@SpringBootApplication
class BootApplication

object App {
  def main(args: Array[String]): Unit = {
    if (args.contains("-h") || args.contains("--help")) {
      println(s"SPS Rest Server v${Server.VERSION}\nAll rights reserved\nUsage: rest-server.jar [OPTIONS]\nOptions:")
      println(s"  -h,--help             - Prints this message\n  -c FILE,--config FILE - Config file path")
      return
    }

    var index = -1

    if (args.contains("-c")) {
      index = args.indexOf("-c") + 1
    } else if (args.contains("--config")) {
      index = args.indexOf("--config") + 1
    }

    if (index != -1) {
      println(s"Config file: ${args(index)}")
      Config.set(Config.fromFile(args(index)))
    }

    println(s"SPS Rest Server v${Server.VERSION}\n")
    SpringApplication.run(classOf[BootApplication])
  }
}
