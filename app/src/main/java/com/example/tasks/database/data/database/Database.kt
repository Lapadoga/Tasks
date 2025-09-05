package com.example.tasks.database.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasks.database.data.dao.BouquetDAO
import com.example.tasks.database.data.dao.FlowerDAO
import com.example.tasks.database.data.entity.BouquetEntity
import com.example.tasks.database.data.entity.FlowerEntity
import com.example.tasks.database.data.migrations.MIGRATION_1_2

@Database(entities = [FlowerEntity::class, BouquetEntity::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract fun bouquetDAO(): BouquetDAO
    abstract fun flowerDAO(): FlowerDAO

    companion object {
        fun getDatabaseInstance(context: Context) = Room.databaseBuilder(
            context = context,
            com.example.tasks.database.data.database.Database::class.java,
            "my_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}