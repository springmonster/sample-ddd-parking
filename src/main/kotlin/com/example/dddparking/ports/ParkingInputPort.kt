package com.example.dddparking.ports

import com.example.dddparking.domain.ParkingOutputPort
import com.example.dddparking.domain.command.*
import com.example.dddparking.domain.event.ParkingEventQueue
import com.example.dddparking.domain.valueobj.Plate
import com.example.dddparking.usecases.ParkingUseCase
import java.time.LocalDateTime

class ParkingInputPort(val outputPort: ParkingOutputPort) : ParkingUseCase {
    val checkInCommandHandler = CheckInCommandHandler(parkingOutputPort = outputPort)
    val calculateFeeCommandHandler = CalculateFeeCommandHandler(parkingOutputPort = outputPort)
    val notifyPayCommandHandler = NotifyPayCommandHandler(parkingOutputPort = outputPort)
    val checkOutCommandHandler = CheckOutCommandHandler(parkingOutputPort = outputPort)
    val eventQueue = ParkingEventQueue()

    override fun checkIn(plate: Plate) {
        val handle = checkInCommandHandler.handle(
            eventQueue = eventQueue, command = CheckInCommand(
                plate,
                LocalDateTime.now().minusHours(2)
            )
        )

        println("checkIn: $handle")
    }

    override fun checkOut(plate: Plate) {
        val fee = calculateFeeCommandHandler.handle(
            command = CalculateFeeCommand(
                plate,
                LocalDateTime.now().minusMinutes(10)
            )
        )
        notifyPayCommandHandler.handle(
            eventQueue = eventQueue, command = NotifyPayCommand(
                plate,
                fee,
                LocalDateTime.now().minusMinutes(10)
            )
        )

        val handle = checkOutCommandHandler.handle(
            eventQueue = eventQueue, command = CheckOutCommand(
                plate,
                LocalDateTime.now()
            )
        )

        println("checkOut: $handle and fee is: $fee")
    }
}
