package com.example.dddparking.domain

import com.example.dddparking.domain.aggregate.Parking
import com.example.dddparking.domain.valueobj.Plate

interface ParkingRepository {
    fun findByIdOrError(plate: Plate): Parking
    fun save(parking: Parking)
}
