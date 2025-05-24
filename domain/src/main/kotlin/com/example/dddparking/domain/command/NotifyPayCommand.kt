package com.example.dddparking.domain.command

import com.example.dddparking.domain.event.EventQueue
import com.example.dddparking.domain.ParkingOutputPort
import com.example.dddparking.domain.valueobj.Plate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

// 计费命令
class NotifyPayCommand(
    val plate: Plate,
    val amount: Int,
    val payTime: LocalDateTime
)

@Component
class NotifyPayCommandHandler(
    private val parkingOutputPort: ParkingOutputPort
) {

    fun handle(eventQueue: EventQueue, command: NotifyPayCommand) {
        val parking = parkingOutputPort.findByIdOrError(command.plate)
        parking.handle(eventQueue, command)
        parkingOutputPort.save(parking)
    }

}
