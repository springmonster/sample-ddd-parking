package com.example.dddparking.domain.aggregate

import com.example.dddparking.domain.DomainException
import com.example.dddparking.domain.command.CheckInCommand
import com.example.dddparking.domain.command.CheckOutCommand
import com.example.dddparking.domain.command.NotifyPayCommand
import com.example.dddparking.domain.event.*
import com.example.dddparking.domain.valueobj.Plate
import java.time.Duration
import java.time.LocalDateTime

class ParkingImpl(
    // 值对象
    private val plateAsId: Plate,
    private var checkInTime: LocalDateTime?,
    private var lastPayTime: LocalDateTime?,
    private var totalPaid: Int = 0
) : Parking {

    override fun getPlate(): Plate {
        return plateAsId
    }

    override fun handle(eventQueue: EventQueue, command: CheckInCommand): Boolean {
        if (inPark()) {
            eventQueue.enqueue(CheckInFailedEvent(plateAsId, command.checkInTime))
            return false
        }

        eventQueue.enqueue(CheckedInEvent(plateAsId, command.checkInTime))
        this.checkInTime = command.checkInTime
        return true
    }

    override fun handle(eventQueue: EventQueue, command: NotifyPayCommand) {
        if (!inPark()) {
            throw DomainException("车辆不在场，不能付费")
        }

        lastPayTime = command.payTime
        totalPaid += command.amount

        eventQueue.enqueue(PaidEvent(plate = plateAsId, amount = command.amount, payTime = command.payTime))
    }

    override fun handle(eventQueue: EventQueue, command: CheckOutCommand): Boolean {
        if (!inPark()) {
            eventQueue.enqueue(CheckOutFailedEvent(plate = plateAsId, time = command.time, message = "车辆不在场"))
            return false
        }

        if (calculateFeeNow(command.time) > 0) {
            return false
        }

        this.checkInTime = null
        this.totalPaid = 0
        this.lastPayTime = null

        eventQueue.enqueue(CheckedOutEvent(plate = plateAsId, time = command.time))
        return true
    }

    override fun calculateFeeNow(now: LocalDateTime): Int {
        val currentCheckInTime = checkInTime ?: throw DomainException("车辆尚未入场")
        val lastPayTimeCurrent = lastPayTime ?: return hoursBetween(currentCheckInTime, now)
        if (lastPayTimeCurrent.plusMinutes(15).isAfter(now)) {
            return 0
        }

        return hoursBetween(currentCheckInTime, now) - totalPaid
    }

    fun hoursBetween(start: LocalDateTime, end: LocalDateTime): Int {
        val minutes = Duration.between(start, end).toMinutes()
        val hours = minutes / 60
        return (if (hours * 60 == minutes) hours else hours + 1).toInt()
    }

    fun inPark(): Boolean {
        return checkInTime != null
    }
}
