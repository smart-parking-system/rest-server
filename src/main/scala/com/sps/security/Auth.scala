package com.sps.security

import com.sps.config.{Config, User}
import com.sps.security.Encryption

object Auth {
  /**
   * Authenticates user
   * Returns true if user exists & passwords match, false otherwise
   * @param config - Rest Server Config (stores user table & encryption)
   * @param authData - User data required for authentication (format: USER:PASS)
   * @return (Boolean, String) - _1: auth status, _2: error message
   */
  def authenticate(config: Config, authData: String): (Boolean, String) = {
    var userData = Array[String]()
    try {
      userData = config.encryption.decrypt(authData).split(":")
    } catch {
      case _ => return (false, "Bad key")
    }
    if (userData.length != 2) {
      return (false, "Wrong user data format")
    }
    val user = User(userData(0), userData(1))

    config.users.find(u => u.name == user.name) match {
      case Some(u) => if (u.pass == user.pass) return (true, "Ok") else return (false, "Passwords do not match")
      case None => return (false, "No such user")
    }
  }
}
