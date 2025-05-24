package com.example.dddparking.domain.service

import com.example.dddparking.domain.event.CheckInFailedEvent
import com.example.dddparking.domain.event.CheckOutFailedEvent
import com.example.dddparking.domain.event.DomainEvent
import com.example.dddparking.domain.event.DomainEventListener
import org.springframework.stereotype.Component

// 领域服务
@Component
class AlarmPolicy(
    private val alarmService: AlarmService
) : DomainEventListener {
    override fun onEvent(event: DomainEvent) {
        if (event is CheckInFailedEvent) {
            alarmService.alarm(event.plate, "入场失败")
            return
        }

        if (event is CheckOutFailedEvent) {
            alarmService.alarm(event.plate, event.message)
        }
    }
}
