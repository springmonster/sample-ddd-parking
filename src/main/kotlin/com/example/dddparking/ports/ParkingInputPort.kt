package com.example.dddparking.ports

import com.example.dddparking.domain.valueobj.Plate
import com.example.dddparking.usecases.ParkingUsecase

class ParkingInputPort: ParkingUsecase {
    override fun checkIn(plate: Plate) {
        TODO("Not yet implemented")
    }

    override fun checkOut(plate: Plate) {
        TODO("Not yet implemented")
    }

    override fun calculateFee(plate: Plate) {
        TODO("Not yet implemented")
    }
}
