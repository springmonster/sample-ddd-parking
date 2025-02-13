package com.example.dddparking.domain

// 领域服务
interface AlarmService {
    fun alarm(plate: Plate, message: String)
}
