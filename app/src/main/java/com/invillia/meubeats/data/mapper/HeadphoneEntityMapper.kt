package com.invillia.meubeats.data.mapper

interface HeadphoneEntityMapper<Entity, DomainModel> {

    fun toDomain(entity: Entity): DomainModel

    fun toEntity(domainModel: DomainModel): Entity

    fun toDomainList(entityList: List<Entity>) = entityList.map { toDomain(it) }

    fun toEntityList(domainList: List<DomainModel>) = domainList.map { toEntity(it) }
}