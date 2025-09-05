package com.example.tasks.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.example.tasks.database.data.model.FlowerType

@Entity(
    tableName = "flowers",
    foreignKeys = [
        ForeignKey(
            entity = BouquetEntity::class,
            parentColumns = ["id"],
            childColumns = ["bouquet_id"],
            onDelete = CASCADE
        )
    ]
)
data class FlowerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val type: FlowerType,
    @ColumnInfo(name = "bouquet_id", index = true)
    val bouquetId: Int? = null,
    // Добавлено во второй версии
    val country: String = "",
)
