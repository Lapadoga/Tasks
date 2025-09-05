package com.example.tasks.pattern.factory

import com.example.tasks.pattern.Car
import com.example.tasks.pattern.parts.Engine

interface CarFactory {
    fun createEngine(): Engine
    fun assembleCar(mark: String, mileage: Int, color: String): Car
}