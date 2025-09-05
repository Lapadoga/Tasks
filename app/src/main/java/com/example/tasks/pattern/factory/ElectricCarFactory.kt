package com.example.tasks.pattern.factory

import com.example.tasks.pattern.Car
import com.example.tasks.pattern.parts.ElectricEngine
import com.example.tasks.pattern.parts.Engine

class ElectricCarFactory : CarFactory {
    override fun createEngine(): Engine = ElectricEngine()

    override fun assembleCar(
        mark: String,
        mileage: Int,
        color: String
    ): Car {
        return Car.Builder()
            .setColor(color)
            .setMark(mark)
            .setMileage(mileage)
            .setEngine(createEngine())
            .build()
    }
}