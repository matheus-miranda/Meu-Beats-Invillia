package com.invillia.meubeats.domain.usecase

import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.domain.repository.BeatsRepository
import kotlinx.coroutines.flow.Flow

class SearchDatabaseUseCase(private val repository: BeatsRepository) {
    operator fun invoke(searchQuery: String): Flow<List<Headphone>> {
        return repository.searchDatabase(searchQuery)
    }
}