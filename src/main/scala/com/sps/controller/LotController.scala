package com.sps.controller

import com.sps.controller.Controller
import com.sps.service.LotService
import com.sps.security.Auth
import com.sps.server.{Server, Logger}
import com.sps.config.Config
import com.sps.dto.LotDto
import com.sps.model.Lot

import scala.jdk.CollectionConverters._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{ResponseEntity, HttpStatus, MediaType}
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, PutMapping, RequestBody, RequestMapping, RestController, RequestHeader}

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
  def createLot(@RequestHeader("X_SPS_USER_DATA") userData: String,
                @RequestBody lot: Lot): ResponseEntity[LotDto] =
    Controller.executeAuthenticated[LotDto](userData, new ResponseEntity(new LotDto(lotService.create(lot)), HttpStatus.OK))

  @PutMapping(path = Array("/{id}"))
  def updateLot(@RequestHeader("X_SPS_USER_DATA") userData: String,
                @PathVariable("id") id: Int, @RequestBody lot: Lot): ResponseEntity[LotDto] =
    Controller.executeAuthenticated[LotDto](userData, {
      lotService.get(id) match {
        case Some(value) =>
          lot.id = id
          new ResponseEntity(new LotDto(lotService.create(lot)), HttpStatus.OK)
        case None =>
          new ResponseEntity(HttpStatus.NOT_FOUND)
      }
    })

  @DeleteMapping(path = Array("/{id}"))
  def deleteLot(@RequestHeader("X_SPS_USER_DATA") userData: String,
                @PathVariable("id") id: Int): ResponseEntity[LotDto] =
    Controller.executeAuthenticated[LotDto](userData, {
      lotService.delete(id)
      ResponseEntity.noContent().build()
    })
}
