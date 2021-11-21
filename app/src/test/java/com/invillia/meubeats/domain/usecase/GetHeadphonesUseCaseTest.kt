package com.invillia.meubeats.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.core.Resource
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

class GetHeadphonesUseCaseTest {

    @MockK
    private lateinit var repository: BeatsRepository

    @InjectMockKs
    private lateinit var getHeadphonesUseCase: GetHeadphonesUseCase

    @Before
    fun setUp() = initMockkAnnotations()

    @Test
    fun `should emit a Flow of Resource Headphone objects when Repository is called`() {
        val expectedResult = flow { emit(Resource.Success(listOf(TestData.HEADPHONE))) }
        every { repository.getHeadphones() } returns expectedResult

        val returnedList = getHeadphonesUseCase.invoke()

        verify(exactly = 1) { repository.getHeadphones() }
        assertThat(expectedResult).isEqualTo(returnedList)
    }
}