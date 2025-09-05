package com.example.tasks.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bouquets")
data class BouquetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    // Добавлено во второй версии
    val decor: String = "",
)
