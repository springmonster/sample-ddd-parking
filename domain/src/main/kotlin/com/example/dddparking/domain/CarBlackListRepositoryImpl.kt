package com.example.dddparking.domain

import org.springframework.stereotype.Component

@Component
class CarBlackListRepositoryImpl:CarBlackListRepository {
    override fun findByIdOrError(plate: Plate): CarBlackList {
        TODO("Not yet implemented")
    }

    override fun save(blackList: CarBlackList) {
        TODO("Not yet implemented")
    }
}
