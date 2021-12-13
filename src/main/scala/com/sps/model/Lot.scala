package com.sps.model

import javax.persistence.*

@Entity
@Table(name = "lot")
class Lot extends Model {

  @Id
  var id: Int = 0
  var parkingId: Int = 0
  var status: Int = 0
  var internalId: Int = 0

  def setId(newId: Int) = id = newId
  def setParkingId(newParkingId: Int) = parkingId = newParkingId
  def setStatus(newStatus: Int) = status = newStatus
  def setInternalId(newInternalId: Int) = internalId = newInternalId

  override def toString(): String = s"Lot($id, $parkingId, $status, $internalId)"
}

object ParkingLotStatus extends Enumeration {
  val FREE = Value(0);
  val OCCUPIED = Value(1);
}
