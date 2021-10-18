package com.sps.db

import com.sps.config.Config

case class ConnectionInfo(var driver: String = "org.postgresql.Driver",
                          val url: String = "jdbc:postgresql:sps",
                          val username: String = "",
                          val password: String = "")

object ConnectionInfo {
  def fromConfig(config: Config): ConnectionInfo = {
    ConnectionInfo(url = config.dbUrl, username = config.dbUsername, password = config.dbPassword)
  }
}
