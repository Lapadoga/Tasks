package com.example.tasks.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tasks.database.data.entity.FlowerEntity

@Dao
interface FlowerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAllFlowers(flowers: List<FlowerEntity>)

    @Query("SELECT * FROM flowers WHERE bouquet_id = NULL")
    suspend fun getAvailableFlower(): List<FlowerEntity>

    @Query("SELECT * FROM flowers WHERE id = :id")
    fun getFlowerById(id: Int): FlowerEntity?
}