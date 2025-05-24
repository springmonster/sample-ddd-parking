package com.example.dddparking.domain.command

import com.example.dddparking.domain.ParkingOutputPort
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
    private val parkingOutputPort: ParkingOutputPort
) {
    fun handle(command: CalculateFeeCommand): Int {
        val parking = this.parkingOutputPort.findByIdOrError(command.plate)
        return parking.calculateFeeNow(command.time)
    }
}
