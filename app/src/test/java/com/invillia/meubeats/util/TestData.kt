package com.invillia.meubeats.util

import com.invillia.meubeats.data.local.entity.HeadphoneEntity
import com.invillia.meubeats.data.remote.dto.NetworkHeadphone
import com.invillia.meubeats.domain.model.Headphone
import io.mockk.MockKAnnotations

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

    fun Any.initMockkAnnotations() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}