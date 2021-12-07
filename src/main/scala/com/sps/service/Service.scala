package com.sps.service

import com.sps.model.Model
import org.springframework.data.jpa.repository.JpaRepository

trait Service[T <: Model] {
  val repository: JpaRepository[T, Int]

  def get(id: Int): Option[T]
  def getAll(): List[T]
  def update(value: T): T
  def create(value: T): T
  def delete(id: Int): Unit
}
