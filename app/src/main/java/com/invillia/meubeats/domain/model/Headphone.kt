package com.invillia.meubeats.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Headphone(
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
) : Parcelable {

    override fun toString(): String {
        return "Headphone(model='$model', rating=$rating, price=$price, totalReviews=$totalReviews, " +
                "image='$image', connection='$connection', compatibility='$compatibility', " +
                "charge='$charge', autonomy='$autonomy', height='$height', capture='$capture')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Headphone

        if (model != other.model) return false
        if (rating != other.rating) return false
        if (price != other.price) return false
        if (totalReviews != other.totalReviews) return false
        if (image != other.image) return false
        if (connection != other.connection) return false
        if (compatibility != other.compatibility) return false
        if (charge != other.charge) return false
        if (autonomy != other.autonomy) return false
        if (height != other.height) return false
        if (capture != other.capture) return false

        return true
    }

    override fun hashCode(): Int {
        var result = model.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + totalReviews
        result = 31 * result + image.hashCode()
        result = 31 * result + connection.hashCode()
        result = 31 * result + compatibility.hashCode()
        result = 31 * result + charge.hashCode()
        result = 31 * result + autonomy.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + capture.hashCode()
        return result
    }

}