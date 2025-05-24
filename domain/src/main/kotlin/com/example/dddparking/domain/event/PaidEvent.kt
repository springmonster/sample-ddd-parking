package com.example.dddparking.domain.event

import com.example.dddparking.domain.valueobj.Plate
import java.time.LocalDateTime

class PaidEvent (
    val plate: Plate,
    val amount: Int,
    val payTime: LocalDateTime
): DomainEvent
