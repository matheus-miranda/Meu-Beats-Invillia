package com.invillia.meubeats.domain.repository

import com.invillia.meubeats.core.Resource
import com.invillia.meubeats.domain.model.Headphone
import kotlinx.coroutines.flow.Flow

interface BeatsRepository {
    fun getHeadphones(): Flow<Resource<List<Headphone>>>

    suspend fun saveToDb(headphones: Array<Headphone>)
}