package com.invillia.meubeats.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.invillia.meubeats.data.local.entity.HeadphoneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeadphoneDao {
    @Query("SELECT * FROM Headphones")
    fun getAll(): Flow<List<HeadphoneEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg headphones: HeadphoneEntity)

    @Query("DELETE FROM Headphones")
    suspend fun deleteAll()
}