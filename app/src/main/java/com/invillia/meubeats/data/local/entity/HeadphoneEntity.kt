package com.invillia.meubeats.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Headphones")
data class HeadphoneEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "HeadphoneID")
    var id: Int = 0,

    @ColumnInfo(name = "Model")
    val model: String,

    @ColumnInfo(name = "Rating")
    val rating: Double,

    @ColumnInfo(name = "Price")
    val price: Double,

    @ColumnInfo(name = "TotalReviews")
    val totalReviews: Int,

    @ColumnInfo(name = "Image")
    val image: String,

    @ColumnInfo(name = "Connection")
    val connection: String,

    @ColumnInfo(name = "Compatibility")
    val compatibility: String,

    @ColumnInfo(name = "Charge")
    val charge: String,

    @ColumnInfo(name = "Autonomy")
    val autonomy: String,

    @ColumnInfo(name = "Height")
    val height: String,

    @ColumnInfo(name = "Capture")
    val capture: String
)
