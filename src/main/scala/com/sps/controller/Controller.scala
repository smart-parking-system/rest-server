package com.sps.controller

import com.sps.dto.Dto
import com.sps.model.Model
import com.sps.config.Config
import com.sps.security.Auth

import org.springframework.http.{ResponseEntity, HttpStatus}

trait Controller[T <: Model, D <: Dto]

object Controller {
  def executeAuthenticated[T](userData: String, successValue: ResponseEntity[T]): ResponseEntity[T] = {
    return (Auth.authenticate(Config.get(), userData) match {
      case (false, msg: String) => ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg)
      case (true, msg: String) => successValue
    }).asInstanceOf[ResponseEntity[T]]
  }
}
