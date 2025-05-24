package com.example.dddparking.usecases

import com.example.dddparking.domain.valueobj.Plate

interface ParkingUseCase {
    fun checkIn(plate: Plate)
    fun checkOut(plate: Plate)
}
