package com.example.dddparking.domain.event

interface DomainEventListener {
    fun onEvent(event: DomainEvent)
}
