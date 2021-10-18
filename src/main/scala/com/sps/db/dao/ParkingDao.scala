package com.sps.db.dao

import com.sps.db.{ConnectionInfo, PrimaryKey, Table}
import com.sps.db.impl.DaoImpl
import com.sps.db.model.Parking
import com.sps.Reflection

class ParkingDao(connInfo: ConnectionInfo) extends DaoImpl[Parking](connInfo,
  Reflection.findAnnotationOrDie[Parking, Table].getName(),
  Reflection.findAnnotationOrDie[Parking, PrimaryKey].getPrimaryKeyName(),
  () => new Parking)
