package com.invillia.meubeats.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.invillia.meubeats.data.local.dao.HeadphoneDao
import com.invillia.meubeats.data.local.entity.HeadphoneEntity

@Database(entities = [HeadphoneEntity::class], version = 1, exportSchema = false)
abstract class BeatsDatabase : RoomDatabase() {
    abstract val headphoneDao: HeadphoneDao

    companion object {
        fun getInstance(context: Context): BeatsDatabase {
            return Room.databaseBuilder(context, BeatsDatabase::class.java, "beats.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}