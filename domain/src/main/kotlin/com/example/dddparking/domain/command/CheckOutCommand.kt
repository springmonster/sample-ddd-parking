package com.example.dddparking.domain.command

import com.example.dddparking.domain.event.EventQueue
import com.example.dddparking.domain.ParkingRepository
import com.example.dddparking.domain.valueobj.Plate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

// 出场命令
class CheckOutCommand(
    val plate: Plate,
    val time: LocalDateTime
)

@Component
class CheckOutCommandHandler(
    private val parkingRepository: ParkingRepository
) {

    fun handle(eventQueue: EventQueue, command: CheckOutCommand): Boolean {
        val parking = parkingRepository.findByIdOrError(command.plate)
        val result = parking.handle(eventQueue, command)
        parkingRepository.save(parking)

        return result
    }

}
