package com.example.dddparking.domain.event

import com.example.dddparking.domain.valueobj.Plate
import java.time.LocalDateTime

class CheckedInEvent(
    val plate: Plate,
    val time: LocalDateTime
): DomainEvent
