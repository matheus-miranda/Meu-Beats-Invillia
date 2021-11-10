package com.invillia.meubeats.util

import com.invillia.meubeats.domain.model.Headphone

object TestData {

    val HEADPHONE = Headphone(
        model = "model",
        rating = 1.1,
        price = 2.99,
        totalReviews = 10,
        image = "image.jpg",
        connection = "connection",
        compatibility = "compatibility",
        charge = "charge",
        autonomy = "autonomy",
        height = "height",
        capture = "capture"
    )
}