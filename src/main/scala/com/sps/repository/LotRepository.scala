package com.sps.repository

import com.sps.model.Lot
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
trait LotRepository extends JpaRepository[Lot, Int]
