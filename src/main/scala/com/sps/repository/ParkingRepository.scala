package com.sps.repository

import com.sps.model.Parking
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
trait ParkingRepository extends JpaRepository[Parking, Int]
