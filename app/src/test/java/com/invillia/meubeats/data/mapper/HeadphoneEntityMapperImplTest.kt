package com.invillia.meubeats.data.mapper

import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.util.TestData.ENTITY_HEADPHONE
import com.invillia.meubeats.util.TestData.HEADPHONE
import org.junit.Test

class HeadphoneEntityMapperImplTest {

    private val mapper = HeadphoneEntityMapperImpl()

    @Test
    fun toDomain() {
        val expectedResult = HEADPHONE
        val returnedResult = mapper.toDomain(ENTITY_HEADPHONE)
        assertThat(returnedResult).isEqualTo(expectedResult)
    }

    @Test
    fun toEntity() {
        val expectedResult = ENTITY_HEADPHONE
        val returnedResult = mapper.toEntity(HEADPHONE)
        assertThat(returnedResult).isEqualTo(expectedResult)
    }

    @Test
    fun toDomainList() {
        val expectedResult = listOf(HEADPHONE)
        val returnedResult = mapper.toDomainList(listOf(ENTITY_HEADPHONE))
        assertThat(returnedResult).isEqualTo(expectedResult)
    }

    @Test
    fun toEntityList() {
        val expectedResult = listOf(ENTITY_HEADPHONE)
        val returnedResult = mapper.toEntityList(listOf(HEADPHONE))
        assertThat(returnedResult).isEqualTo(expectedResult)
    }
}