package com.example.dddparking.domain.command

import com.example.dddparking.domain.event.EventQueue
import com.example.dddparking.domain.ParkingOutputPort
import com.example.dddparking.domain.valueobj.Plate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

// 入场命令
class CheckInCommand(
    val plate: Plate,
    val checkInTime: LocalDateTime
)

@Component
class CheckInCommandHandler(
    private val parkingOutputPort: ParkingOutputPort
) {
    fun handle(eventQueue: EventQueue, command: CheckInCommand): Boolean {
        val parking = parkingOutputPort.findByIdOrError(command.plate)
        val result = parking.handle(eventQueue, command)
        parkingOutputPort.save(parking)

        return result
    }
}
