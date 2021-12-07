package com.sps.service

import com.sps.service.impl.ServiceImpl
import com.sps.model.Lot
import com.sps.repository.LotRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class LotService extends ServiceImpl[Lot] {
  @Autowired
  override val repository: LotRepository = null
}
