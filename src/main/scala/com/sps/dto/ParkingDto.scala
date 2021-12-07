package com.sps.dto

import com.sps.model.Parking

class ParkingDto(parking: Parking) extends Dto {
  def getId = parking.id
  def getName = parking.name
  def getLocation = parking.location
}
