package com.example.dddparking.domain

import com.example.dddparking.domain.aggregate.Parking
import com.example.dddparking.domain.valueobj.Plate

// 如果是六边形
// 这里其实是port，具体由adapter实现
interface ParkingOutputPort {
    fun findByIdOrError(plate: Plate): Parking
    fun save(parking: Parking)
}

//interface ParkingRepository {
//    fun findByIdOrError(plate: Plate): Parking
//    fun save(parking: Parking)
//}
