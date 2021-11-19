package com.invillia.meubeats.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invillia.meubeats.core.Resource
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.domain.usecase.GetHeadphonesUseCase
import com.invillia.meubeats.domain.usecase.GetNetworkHeadphonesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val getNetworkHeadphonesUseCase: GetNetworkHeadphonesUseCase,
    private val getHeadphonesUseCase: GetHeadphonesUseCase
) :
    ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _headphoneState = MutableStateFlow<UiState>(UiState.EmptyList)
    val headphoneState: StateFlow<UiState> = _headphoneState.asStateFlow()

    private val _navigateToProductDetails = MutableStateFlow<Headphone?>(null)
    val navigateToProductDetails: StateFlow<Headphone?> = _navigateToProductDetails.asStateFlow()

    init {
        getNetworkHeadphones()
        getHeadphones()
    }

    private fun getNetworkHeadphones() = viewModelScope.launch {
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

    private fun getHeadphones() = viewModelScope.launch {
        getHeadphonesUseCase()
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _headphoneState.value = UiState.Loading(list = result.data)
                    }
                    is Resource.Error -> {
                        _headphoneState.value =
                            UiState.Error(list = result.data, error = result.message)
                    }
                    is Resource.Success -> {
                        _headphoneState.value = UiState.Success(list = result.data)
                    }
                }
            }.launchIn(this)
    }

    fun onProductClicked(headphone: Headphone) {
        _navigateToProductDetails.value = headphone
    }

    fun doneNavigatingToProductDetails() {
        _navigateToProductDetails.value = null
    }
}