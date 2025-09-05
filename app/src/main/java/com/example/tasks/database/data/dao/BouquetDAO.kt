package com.example.tasks.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tasks.database.data.entity.BouquetEntity

@Dao
interface BouquetDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAllBouquets(bouquets: List<BouquetEntity>)

    @Delete
    suspend fun deleteAllBouquets(bouquets: List<BouquetEntity>)

    @Query("SELECT DISTINCT * FROM bouquets INNER JOIN flowers ON bouquet_id")
    suspend fun getAvailableBouquets(): List<BouquetEntity>
}