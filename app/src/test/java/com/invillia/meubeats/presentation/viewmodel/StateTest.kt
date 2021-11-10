package com.invillia.meubeats.presentation.viewmodel

import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.util.TestData
import org.junit.Test

class StateTest {

    @Test
    fun success() {
        val list = listOf(TestData.HEADPHONE)
        val success = State.Success(list)
        assertThat(list).isEqualTo(success.headphoneList)
    }

    @Test
    fun error() {
        val throwable = Throwable("Error Message")
        val error = State.Error(throwable)
        assertThat(throwable).isEqualTo(error.throwable)
    }
}