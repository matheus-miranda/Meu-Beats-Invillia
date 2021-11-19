package com.invillia.meubeats.presentation.viewmodel

import com.invillia.meubeats.domain.model.Headphone

sealed class State {
    object Loading : State()
    object EmptyList : State()
    data class Success(val headphoneList: List<Headphone>) : State()
    data class Error(val throwable: Throwable) : State()
}

sealed class UiState {
    object EmptyList : UiState()
    data class Loading(val list: List<Headphone>? = emptyList()) : UiState()
    data class Success(val list: List<Headphone>? = emptyList()) : UiState()
    data class Error(val list: List<Headphone>? = emptyList(), val error: String? = "") : UiState()
}