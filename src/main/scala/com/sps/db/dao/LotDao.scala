package com.sps.db.dao

import com.sps.db.{ConnectionInfo, PrimaryKey, Table}
import com.sps.db.impl.DaoImpl
import com.sps.db.model.Lot
import com.sps.Reflection

class LotDao(connInfo: ConnectionInfo) extends DaoImpl[Lot](connInfo,
  Reflection.findAnnotationOrDie[Lot, Table].getName(),
  Reflection.findAnnotationOrDie[Lot, PrimaryKey].getPrimaryKeyName(),
  () => new Lot)
