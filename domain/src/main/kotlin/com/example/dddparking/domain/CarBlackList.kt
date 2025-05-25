package com.example.dddparking.domain

interface CarBlackList {
    fun isBaned(): Boolean
    fun add(eventQueue: EventQueue, addCarToBlackListCommand: AddCarToBlackListCommand)
    fun remove(eventQueue: EventQueue, removeCarFromBlackListCommand: RemoveCarFromBlackListCommand)
}

class CarBlackListImpl(
    private var plate: Plate,
    private var isBaned: Boolean
) : CarBlackList {

    override fun isBaned(): Boolean {
        return isBaned
    }

    override fun add(eventQueue: EventQueue, addCarToBlackListCommand: AddCarToBlackListCommand) {
        if (isBaned()) {
            throw IllegalArgumentException("Plate is already in the black list")
        }
        eventQueue.enqueue(CarAddedToBlackListEvent(plate))
    }

    override fun remove(eventQueue: EventQueue, removeCarFromBlackListCommand: RemoveCarFromBlackListCommand) {
        if (!isBaned()) {
            throw IllegalArgumentException("Plate is not in the black list")
        }
        eventQueue.enqueue(CarRemoveFromBlackListEvent(plate))
    }
}
