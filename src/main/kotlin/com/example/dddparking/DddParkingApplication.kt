package com.example.dddparking

import com.example.dddparking.adapters.ParkingInputCLIAdapter
import com.example.dddparking.domain.ParkingOutputMemoryAdapter
import com.example.dddparking.ports.ParkingInputPort

//@SpringBootApplication
class DddParkingApplication

fun main(args: Array<String>) {
//    runApplication<DddParkingApplication>(*args)
    val parkingOutputPort = ParkingOutputMemoryAdapter()
    val parkingInputPort = ParkingInputPort(parkingOutputPort)
    val parkingUsecase = ParkingInputCLIAdapter(parkingInputPort)

    parkingUsecase.startCheckIn()
    parkingUsecase.startCheckOut()
}
