package com.invillia.meubeats.util

import com.invillia.meubeats.data.local.entity.HeadphoneEntity
import com.invillia.meubeats.data.remote.dto.NetworkHeadphone
import com.invillia.meubeats.domain.model.Headphone

object TestProducts {

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

    val NETWORK_HEADPHONE = NetworkHeadphone(
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

    val ENTITY_HEADPHONE = HeadphoneEntity(
        id = 1,
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

    val ENTITY_HEADPHONE_TWO = HeadphoneEntity(
        id = 2,
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