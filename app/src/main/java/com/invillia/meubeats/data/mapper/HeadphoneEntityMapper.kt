package com.invillia.meubeats.data.mapper

import com.invillia.meubeats.data.local.entity.HeadphoneEntity
import com.invillia.meubeats.domain.model.Headphone

class HeadphoneEntityMapper : EntityMapper<HeadphoneEntity, Headphone> {
    override fun toDomain(entity: HeadphoneEntity) = Headphone(
        model = entity.model,
        rating = entity.rating,
        price = entity.price,
        totalReviews = entity.totalReviews,
        image = entity.image,
        connection = entity.connection,
        compatibility = entity.compatibility,
        charge = entity.charge,
        autonomy = entity.autonomy,
        height = entity.height,
        capture = entity.capture
    )

    override fun toEntity(domainModel: Headphone) = HeadphoneEntity(
        model = domainModel.model,
        rating = domainModel.rating,
        price = domainModel.price,
        totalReviews = domainModel.totalReviews,
        image = domainModel.image,
        connection = domainModel.connection,
        compatibility = domainModel.compatibility,
        charge = domainModel.charge,
        autonomy = domainModel.autonomy,
        height = domainModel.height,
        capture = domainModel.capture
    )
}