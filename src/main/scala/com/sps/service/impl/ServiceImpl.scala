package com.sps.service.impl

import com.sps.model.Model
import com.sps.service.Service

import scala.jdk.OptionConverters.*
import scala.jdk.CollectionConverters.*
import org.springframework.data.jpa.repository.JpaRepository

abstract class ServiceImpl[T <: Model] extends Service[T] {
  def get(id: Int): Option[T] = repository.findById(id).toScala
  def getAll(): List[T] = repository.findAll().asScala.toList
  def update(value: T): T = repository.save(value)
  def create(value: T): T = repository.save(value)
  def delete(id: Int): Unit = repository.deleteById(id)
}
