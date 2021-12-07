package com.sps.dto

import com.sps.model.Lot

class LotDto(lot: Lot) extends Dto {
  def getId = lot.id
  def getParkingId = lot.parkingId
  def getStatus = lot.status
  def getInternalId = lot.internalId
}

