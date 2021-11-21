package com.invillia.meubeats.presentation.viewmodel.util

import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.presentation.util.UiState
import com.invillia.meubeats.util.TestData
import org.junit.Test

class HeadphoneStateTest {

    @Test
    fun success() {
        val list = listOf(TestData.HEADPHONE)

        val success = UiState.Success(list)

        assertThat(list).isEqualTo(success.list)
    }

    @Test
    fun error() {
        val errorMessage = "Error Message"
        val list = listOf(TestData.HEADPHONE)
        val expectedState = UiState.Error(list, errorMessage)

        val returnedState = UiState.Error(list = list, error = errorMessage)

        assertThat(expectedState).isEqualTo(returnedState)
    }

    @Test
    fun loading() {
        val list = listOf(TestData.HEADPHONE)
        val expectedState = UiState.Loading(list)

        val returnedState = UiState.Loading(list = list)

        assertThat(expectedState).isEqualTo(returnedState)
    }
}