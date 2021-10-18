package com.sps.server

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Logger {
  private val _logger = LoggerFactory.getLogger(getClass)

  def logger = _logger
}
