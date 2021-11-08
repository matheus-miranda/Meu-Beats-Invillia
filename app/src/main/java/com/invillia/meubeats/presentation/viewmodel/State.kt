package com.invillia.meubeats.presentation.viewmodel

import com.invillia.meubeats.domain.model.Headphone

sealed class State {
    object Loading : State()
    object EmptyList : State()
    data class Success(val headphoneList: List<Headphone>) : State()
    data class Error(val throwable: Throwable) : State()
}