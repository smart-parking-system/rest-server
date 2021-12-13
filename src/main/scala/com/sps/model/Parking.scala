package com.sps.model

import javax.persistence.*

@Entity
@Table(name = "parking")
class Parking extends Model {

  @Id
  var id: Int = 0
  var name: String = ""
  var location: String = ""

  def setId(newId: Int) = id = newId
  def setName(newName: String) = name = newName
  def setLocation(newLocation: String) = location = newLocation

  override def toString(): String = s"Parking($id, $name, $location)"
}
