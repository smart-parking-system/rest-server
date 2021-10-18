package com.sps.server

import java.util.UUID
import java.util.concurrent.*

import scala.collection.mutable.HashMap

import com.sps.server.Logger

class Session(initialLeaseTime: Long = Session.DEFAULT_LEASE_TIME) {
  private val _leaseTime = initialLeaseTime
  private val _creationTime = System.currentTimeMillis()
  private val _uuid = java.util.UUID.randomUUID

  def leaseTime = _leaseTime
  def creationTime = _creationTime
  def uuid = _uuid
}

object Session {
  val DEFAULT_LEASE_TIME = 30000 // 120000 // ms
  val HTTP_HEADER_NAME = "SPS_REST_SESSION_ID"

  private val sessions = new HashMap[String, Session]

  def newSession(): Session = {
    val session = new Session
    sessions.addOne(session.uuid.toString, session)
    session
  }

  def getSession(uuid: String): Option[Session] = sessions.find(_._1.toString == uuid) match {
    case Some(k: String, v: Session) => Option(v)
    case None => Option.empty[Session]
  }

  def updateSessionState(): Unit = {
    Logger.logger.debug("Session State Updater")
    for ((k, v) <- sessions) {
      if (System.currentTimeMillis() - v.creationTime >= v.leaseTime) {
        sessions -= k
      }
    }
  }

}
