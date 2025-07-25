package com.example.tasks.combine

import io.reactivex.rxjava3.core.Observable

data class Card(
    val number: String
)

val observable1 = Observable.just(listOf(Card("1"), Card("123"), Card("456")))
val observable2 = Observable.just(listOf(Card("5"), Card("234"), Card("7867")))

// Решение А
val resultA = Observable.merge(observable1.onErrorComplete(), observable2.onErrorComplete())
// Решение Б
val resultB = Observable.zip(
    observable1.onErrorComplete(),
    observable2.onErrorComplete()
) { list1, list2 ->
    list1 + list2
}
