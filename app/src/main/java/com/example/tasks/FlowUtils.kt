package com.example.tasks

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.throttleFirst(period: Long): Flow<T> {
    require(period > 0) {
        "Period must be positive"
    }
    return flow {
        var lastEmitTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastEmitTime > period) {
                lastEmitTime = currentTime
                emit(value)
            }
        }
    }
}

fun <T> Flow<T>.throttleLatest(period: Long): Flow<T> {
    require(period > 0) {
        "Period must be positive"
    }
    return flow {
        conflate().collect { value ->
            emit(value)
            delay(period)
        }
    }
}