package com.example.dddparking.domain.event

import com.example.dddparking.domain.valueobj.Plate
import java.time.LocalDateTime

class CheckOutFailedEvent(
    val plate: Plate,
    val time: LocalDateTime,
    val message: String
): DomainEvent
