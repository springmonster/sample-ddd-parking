package com.example.dddparking.domain

import com.example.dddparking.domain.event.DomainEvent
import com.example.dddparking.domain.event.EventQueue
import java.util.LinkedList

class TestEventQueue: EventQueue {
    val list = LinkedList<DomainEvent>()
    override fun enqueue(event: DomainEvent) {
        list.add(event)
    }
}
