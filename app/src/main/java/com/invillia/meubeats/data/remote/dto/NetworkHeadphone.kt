package com.invillia.meubeats.data.remote.dto

import com.squareup.moshi.Json

data class NetworkHeadphone(
    @Json(name = "name")
    val model: String,

    @Json(name = "rating")
    val rating: Double,

    @Json(name = "value")
    val price: Double,

    @Json(name = "total_reviews")
    val totalReviews: Int,

    @Json(name = "image")
    val image: String,

    @Json(name = "connection")
    val connection: String,

    @Json(name = "Compatibility")
    val compatibility: String,

    @Json(name = "charge")
    val charge: String,

    @Json(name = "autonomy")
    val autonomy: String,

    @Json(name = "height")
    val height: String,

    @Json(name = "Capture")
    val capture: String
)