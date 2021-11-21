package com.invillia.meubeats.domain.usecase

import com.invillia.meubeats.core.Resource
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.domain.repository.BeatsRepository
import kotlinx.coroutines.flow.Flow

class GetHeadphonesUseCase(private val repository: BeatsRepository) {
    operator fun invoke(): Flow<Resource<List<Headphone>>> {
        return repository.getHeadphones()
    }
}