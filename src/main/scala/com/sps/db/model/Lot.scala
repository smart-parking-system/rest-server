package com.sps.db.model

import com.sps.db.{Model, Table, PrimaryKey}

@Table("lot")
@PrimaryKey("id")
class Lot(var id: Int = 0,
          var parking_id: Int = 0,
          var status: Int = 0,
          var internal_id: Int = 0) extends Model {
  override def toString(): String = s"Lot($id, $parking_id, $status, $internal_id)"

  def getField(name: String): Any = name match {
    case "id"           => id
    case "parking_id"   => parking_id
    case "status"       => status
    case "internal_id"  => internal_id
  }

  def setField(name: String, value: AnyRef): Unit = name match {
    case "id"           => id = value.asInstanceOf[Int]
    case "parking_id"   => parking_id = value.asInstanceOf[Int]
    case "status"       => status = value.asInstanceOf[Int]
    case "internal_id"  => internal_id = value.asInstanceOf[Int]
  }
}

object ParkingLotStatus extends Enumeration {
  val FREE = Value(0);
  val OCCUPIED = Value(1);
}
