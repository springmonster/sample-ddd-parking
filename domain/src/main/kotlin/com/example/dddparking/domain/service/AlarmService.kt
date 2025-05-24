package com.example.dddparking.domain.service

import com.example.dddparking.domain.valueobj.Plate

// 领域服务
interface AlarmService {
    fun alarm(plate: Plate, message: String)
}
