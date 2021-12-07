package com.sps.controller

import com.sps.controller.Controller
import com.sps.service.LotService
import com.sps.dto.LotDto
import com.sps.model.Lot

import scala.jdk.CollectionConverters._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{ResponseEntity, HttpStatus, MediaType}
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, PutMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/lot"))
class LotController extends Controller[Lot, LotDto] {
  @Autowired
  val lotService: LotService = null

  @GetMapping(path = Array("/{id}"))
  def getLot(@PathVariable(name = "id") id: Int): ResponseEntity[LotDto] = lotService.get(id) match {
    case Some(value) => new ResponseEntity(new LotDto(value), HttpStatus.OK)
    case None        => new ResponseEntity(HttpStatus.NOT_FOUND)
  }

  @GetMapping
  def getAllLots(): ResponseEntity[java.util.List[LotDto]] =
    new ResponseEntity(lotService.getAll().map(new LotDto(_)).asJava, HttpStatus.OK)

  @PostMapping
  def createLot(@RequestBody lot: Lot): ResponseEntity[LotDto] =
    new ResponseEntity(new LotDto(lotService.create(lot)), HttpStatus.OK)

  @PutMapping(path = Array("/{id}"))
  def updateLot(@PathVariable("id") id: Int, @RequestBody lot: Lot): ResponseEntity[LotDto] = lotService.get(id) match {
    case Some(value) =>
      lot.id = id
      new ResponseEntity(new LotDto(lotService.create(lot)), HttpStatus.OK)
    case None =>
      new ResponseEntity(HttpStatus.NOT_FOUND)
  }

  @DeleteMapping(path = Array("/{id}"))
  def deleteLot(@PathVariable("id") id: Int): ResponseEntity[Lot] = {
    lotService.delete(id)
    ResponseEntity.noContent().build()
  }
}
