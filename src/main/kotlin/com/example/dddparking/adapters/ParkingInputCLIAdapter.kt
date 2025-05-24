package com.example.dddparking.adapters

import com.example.dddparking.domain.valueobj.Plate
import com.example.dddparking.usecases.ParkingUseCase

class ParkingInputCLIAdapter(val usecase: ParkingUseCase) {

    fun startCheckIn() {
        usecase.checkIn(Plate("A"))
    }

    fun startCheckOut() {
        usecase.checkOut(Plate("A"))
    }
}
