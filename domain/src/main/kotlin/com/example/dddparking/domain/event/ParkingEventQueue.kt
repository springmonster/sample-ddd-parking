package com.example.dddparking.domain.event

import java.util.*

class ParkingEventQueue : EventQueue {
    val list = LinkedList<DomainEvent>()
    override fun enqueue(event: DomainEvent) {
        list.add(event)
    }
}
