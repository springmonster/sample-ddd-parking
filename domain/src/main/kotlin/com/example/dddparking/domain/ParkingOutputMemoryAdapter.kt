package com.example.dddparking.domain

import com.example.dddparking.domain.aggregate.Parking
import com.example.dddparking.domain.aggregate.ParkingImpl
import com.example.dddparking.domain.valueobj.Plate
import java.time.LocalDateTime

// 如果是六边形
// 这里其实是port，具体由adapter实现
class ParkingOutputMemoryAdapter : ParkingOutputPort {
    private val parkings = mutableMapOf<Plate, Parking>()

    //  init parkings, add some data
    init {
        parkings[Plate("A")] = ParkingImpl(
            Plate("A"), LocalDateTime.now().minusDays(2), null, 0
        )

        parkings[Plate("B")] = ParkingImpl(
            Plate("B"), LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1), 100
        )
    }

    override fun findByIdOrError(plate: Plate): Parking {
        return parkings[plate] ?: throw Exception("not found")
    }

    override fun save(parking: Parking) {
        parkings[parking.getPlate()] = parking
    }
}

//interface ParkingRepository {
//    fun findByIdOrError(plate: Plate): Parking
//    fun save(parking: Parking)
//}
