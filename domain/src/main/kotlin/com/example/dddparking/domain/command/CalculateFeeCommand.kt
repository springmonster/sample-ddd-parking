package com.example.dddparking.domain.command

import com.example.dddparking.domain.ParkingRepository
import com.example.dddparking.domain.valueobj.Plate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

// 计算费用命令
class CalculateFeeCommand(
    val plate: Plate,
    val time: LocalDateTime
)

@Component
class CalculateFeeCommandHandler(
    private val parkingRepository: ParkingRepository
) {
    fun handle(command: CalculateFeeCommand): Int {
        val parking = this.parkingRepository.findByIdOrError(command.plate)
        return parking.calculateFeeNow(command.time)
    }
}
