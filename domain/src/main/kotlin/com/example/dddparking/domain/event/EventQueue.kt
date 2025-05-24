package com.example.dddparking.domain.event

interface EventQueue {
    fun enqueue(event: DomainEvent)
}
