package com.example.dddparking.domain

import com.example.dddparking.domain.aggregate.Parking
import com.example.dddparking.domain.aggregate.ParkingImpl
import com.example.dddparking.domain.valueobj.Plate
import java.time.LocalDateTime

// 如果是六边形
// 这里其实是port，具体由adapter实现
class ParkingOutputMemoryAdapter : ParkingOutputPort {
    private val parkings = mutableMapOf<String, Parking>()

    //  init parkings, add some data
    init {
        parkings["A"] = ParkingImpl(
            Plate("A"), null, null, 0
        )

        parkings["B"] = ParkingImpl(
            Plate("B"), null, null, 0
        )
    }

    override fun findByIdOrError(plate: Plate): Parking {
        return parkings[plate.value] ?: throw Exception("not found")
    }

    override fun save(parking: Parking) {
        parkings[parking.getPlate().value] = parking
    }
}

//interface ParkingRepository {
//    fun findByIdOrError(plate: Plate): Parking
//    fun save(parking: Parking)
//}
