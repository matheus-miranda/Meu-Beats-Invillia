package com.invillia.meubeats.data.local.dao

import androidx.room.*
import com.invillia.meubeats.data.local.entity.HeadphoneEntity

@Dao
interface HeadphoneDao {
    @Query("SELECT * FROM Headphones")
    suspend fun getAll(): List<HeadphoneEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg headphones: HeadphoneEntity)

    @Query("DELETE FROM Headphones")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsert(vararg headphones: HeadphoneEntity) {
        deleteAll()
        insert(*headphones)
    }
}