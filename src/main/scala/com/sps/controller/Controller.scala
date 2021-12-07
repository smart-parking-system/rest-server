package com.sps.controller

import com.sps.dto.Dto
import com.sps.model.Model

trait Controller[T <: Model, D <: Dto]
