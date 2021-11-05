package com.invillia.meubeats.domain

data class Headphone(
    val model: String,
    val rating: Double,
    val price: Double,
    val totalReviews: Int,
    val image: String,
    val connection: String,
    val compatibility: String,
    val charge: String,
    val autonomy: String,
    val height: String,
    val capture: String
)