package com.invillia.meubeats.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invillia.meubeats.core.Resource
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.domain.usecase.GetHeadphonesUseCase
import com.invillia.meubeats.domain.usecase.SearchDatabaseUseCase
import com.invillia.meubeats.presentation.util.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val getHeadphonesUseCase: GetHeadphonesUseCase,
    private val searchDatabaseUseCase: SearchDatabaseUseCase
) :
    ViewModel() {

    private val _headphoneState = MutableStateFlow<UiState>(UiState.Loading())
    val headphoneState: StateFlow<UiState> = _headphoneState.asStateFlow()

    private val _navigateToProductDetails = MutableStateFlow<Headphone?>(null)
    val navigateToProductDetails: StateFlow<Headphone?> = _navigateToProductDetails.asStateFlow()

    private val _searchListQuery = MutableStateFlow<List<Headphone>?>(null)
    val searchListQuery: StateFlow<List<Headphone>?> = _searchListQuery.asStateFlow()

    init {
        getHeadphones()
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
                        _headphoneState.value = if (result.data.isNullOrEmpty()) {
                            UiState.EmptyList
                        } else {
                            UiState.Success(list = result.data)
                        }
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

    fun searchDatabase(searchQuery: String) = viewModelScope.launch {
       searchDatabaseUseCase(searchQuery).collect { list ->
           _searchListQuery.value = list
       }
    }
}