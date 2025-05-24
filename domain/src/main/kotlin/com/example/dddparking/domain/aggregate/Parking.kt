package com.example.dddparking.domain.aggregate

import com.example.dddparking.domain.command.CheckInCommand
import com.example.dddparking.domain.command.CheckOutCommand
import com.example.dddparking.domain.command.NotifyPayCommand
import com.example.dddparking.domain.event.EventQueue
import com.example.dddparking.domain.valueobj.Plate
import java.time.LocalDateTime

// kuanghc 聚合
// kuanghc 实体
interface Parking {
    fun getPlate(): Plate
    fun handle(eventQueue: EventQueue, command: CheckInCommand): Boolean
    fun calculateFeeNow(now: LocalDateTime): Int
    fun handle(eventQueue: EventQueue, command: NotifyPayCommand)
    fun handle(eventQueue: EventQueue, command: CheckOutCommand): Boolean
}
