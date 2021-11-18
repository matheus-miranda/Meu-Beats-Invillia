package com.invillia.meubeats.data.mapper

import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.util.TestData.HEADPHONE
import com.invillia.meubeats.util.TestData.NETWORK_HEADPHONE
import org.junit.Test

class NetworkHeadphoneMapperImplTest {

    private val mapper = NetworkHeadphoneMapperImpl()

    @Test
    fun toDomain() {
        val expectedResult = HEADPHONE
        val returnedHeadphone = mapper.toDomain(NETWORK_HEADPHONE)
        assertThat(returnedHeadphone).isEqualTo(expectedResult)
    }

    @Test
    fun toEntity() {
        val expectedResult = NETWORK_HEADPHONE
        val returnedHeadphone = mapper.toEntity(HEADPHONE)
        assertThat(returnedHeadphone).isEqualTo(expectedResult)
    }

    @Test
    fun toDomainList() {
        val expectedResult = listOf(HEADPHONE)
        val returnedHeadphoneList = mapper.toDomainList(listOf(NETWORK_HEADPHONE))
        assertThat(returnedHeadphoneList).isEqualTo(expectedResult)
    }

    @Test
    fun toEntityList() {
        val expectedResult = listOf(NETWORK_HEADPHONE)
        val returnedHeadphoneList = mapper.toEntityList(listOf(HEADPHONE))
        assertThat(returnedHeadphoneList).isEqualTo(expectedResult)
    }
}