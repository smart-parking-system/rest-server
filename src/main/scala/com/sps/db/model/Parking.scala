package com.sps.db.model

import com.sps.db.{Model, Table, PrimaryKey}

@Table("parking")
@PrimaryKey("id")
class Parking(var id: Int = 0,
          var name: String = "",
          var location: String = "") extends Model {
  override def toString(): String = s"Parking($id, $name, $location)"

  def getField(name: String): Any = name match {
    case "id"       => id
    case "name"     => this.name
    case "location" => location
  }

  def setField(name: String, value: AnyRef): Unit = name match {
    case "id"       => id = value.asInstanceOf[Int]
    case "name"     => this.name = value.asInstanceOf[String]
    case "location" => location = value.asInstanceOf[String]
  }
}
