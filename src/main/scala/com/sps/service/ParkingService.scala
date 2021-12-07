package com.sps.service

import com.sps.service.impl.ServiceImpl
import com.sps.model.Parking
import com.sps.repository.ParkingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class ParkingService extends ServiceImpl[Parking] {
  @Autowired
  override val repository: ParkingRepository = null

//  @Query()
//  def getByInternalId(parkingId: Int, internalId: Int): Parking
}
