package com.invillia.meubeats.presentation.util

import com.invillia.meubeats.domain.model.Headphone

sealed class UiState {
    object EmptyList : UiState()
    data class Loading(val list: List<Headphone>? = emptyList()) : UiState()
    data class Success(val list: List<Headphone>?) : UiState()
    data class Error(val list: List<Headphone>? = emptyList(), val error: String? = "") : UiState()
}