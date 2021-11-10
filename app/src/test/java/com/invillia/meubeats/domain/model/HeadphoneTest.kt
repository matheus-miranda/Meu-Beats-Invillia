package com.invillia.meubeats.domain.model

import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.util.TestData
import org.junit.Test

class HeadphoneTest {

    @Test
    fun `instantiate model`() {
        val headphone = TestData.HEADPHONE

        assertThat(headphone.model).isEqualTo("model")
        assertThat(headphone.rating).isEqualTo(1.1)
        assertThat(headphone.price).isEqualTo(2.99)
        assertThat(headphone.totalReviews).isEqualTo(10)
        assertThat(headphone.image).isEqualTo("image.jpg")
        assertThat(headphone.connection).isEqualTo("connection")
        assertThat(headphone.compatibility).isEqualTo("compatibility")
        assertThat(headphone.charge).isEqualTo("charge")
        assertThat(headphone.autonomy).isEqualTo("autonomy")
        assertThat(headphone.height).isEqualTo("height")
        assertThat(headphone.capture).isEqualTo("capture")
    }
}