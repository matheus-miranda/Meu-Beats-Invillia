package com.invillia.meubeats.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.domain.repository.BeatsRepository
import com.invillia.meubeats.util.TestData.initMockkAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test

class GetNetworkHeadphonesUseCaseTest {

    @MockK
    private lateinit var repository: BeatsRepository

    @InjectMockKs
    private lateinit var getNetworkHeadphonesUseCase: GetNetworkHeadphonesUseCase

    @Before
    fun setup() = initMockkAnnotations()

    @Test
    fun `UseCase emits a Flow of Headphone objects when Repository is called once`() {
        val expectedResult = flow { emit(listOf<Headphone>()) }
        every { repository.getNetworkHeadphones() } returns expectedResult

        val result = getNetworkHeadphonesUseCase.invoke()
        verify(exactly = 1) { repository.getNetworkHeadphones() }

        assertThat(result).isEqualTo(expectedResult)
    }

}