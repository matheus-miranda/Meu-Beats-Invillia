package com.invillia.meubeats.domain.repository

import com.invillia.meubeats.domain.model.Headphone
import kotlinx.coroutines.flow.Flow

interface BeatsRepository {
    fun getNetworkHeadphones(): Flow<List<Headphone>>
}