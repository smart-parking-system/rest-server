package com.sps.controller

import com.sps.model.Parking
import com.sps.service.ParkingService
import com.sps.dto.ParkingDto
import com.sps.model.Parking

import scala.jdk.CollectionConverters.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.web.bind.annotation.{DeleteMapping, GetMapping, PathVariable, PostMapping, PutMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/parking"))
class ParkingController extends Controller[Parking, ParkingDto] {
  @Autowired
  val parkingService: ParkingService = null

  @GetMapping(path = Array("/{id}"))
  def getParking(@PathVariable(name = "id") id: Int): ResponseEntity[ParkingDto] = parkingService.get(id) match {
    case Some(value) => new ResponseEntity(new ParkingDto(value), HttpStatus.OK)
    case None        => new ResponseEntity(HttpStatus.NOT_FOUND)
  }

  @GetMapping
  def getAllParkings(): ResponseEntity[java.util.List[ParkingDto]] =
    new ResponseEntity(parkingService.getAll().map(new ParkingDto(_)).asJava, HttpStatus.OK)
}

