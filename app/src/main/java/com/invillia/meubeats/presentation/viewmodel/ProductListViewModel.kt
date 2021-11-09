package com.invillia.meubeats.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.domain.usecase.GetNetworkHeadphonesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductListViewModel(private val getNetworkHeadphonesUseCase: GetNetworkHeadphonesUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _navigateToProductDetails = MutableStateFlow<Headphone?>(null)
    val navigateToProductDetails: StateFlow<Headphone?> = _navigateToProductDetails.asStateFlow()

    init {
        getHeadphones()
    }

    private fun getHeadphones() = viewModelScope.launch {
        getNetworkHeadphonesUseCase()
            .flowOn(Dispatchers.Main)
            .onStart {
                _state.value = State.Loading
            }
            .catch { error ->
                _state.value = State.Error(error)
            }
            .collect { list ->
                if (list.isEmpty()) {
                    _state.value = State.EmptyList
                } else {
                    _state.value = State.Success(list)
                }
            }
    }

    fun onProductClicked(headphone: Headphone) {
        _navigateToProductDetails.value = headphone
    }

    fun doneNavigatingToProductDetails() {
        _navigateToProductDetails.value = null
    }
}