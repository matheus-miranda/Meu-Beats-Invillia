package com.invillia.meubeats.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.domain.repository.BeatsRepository
import com.invillia.meubeats.util.TestData
import com.invillia.meubeats.util.TestData.initMockkAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class SearchDatabaseUseCaseTest {

    @InjectMockKs
    private lateinit var searchDatabaseUseCase: SearchDatabaseUseCase

    @MockK
    private lateinit var repository: BeatsRepository

    @Before
    fun setUp() = initMockkAnnotations()

    @Test
    fun `should emit a flow of list of headphones when repository search is called`() {
        val expectedResult = flow { emit(listOf(TestData.HEADPHONE)) }
        every { repository.searchDatabase(anyString()) } returns expectedResult

        val returnedList = searchDatabaseUseCase.invoke(anyString())

        verify(exactly = 1) { repository.searchDatabase(anyString()) }
        assertThat(returnedList).isEqualTo(expectedResult)
    }
}