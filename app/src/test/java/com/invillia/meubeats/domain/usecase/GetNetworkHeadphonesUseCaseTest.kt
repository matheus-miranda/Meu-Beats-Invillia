package com.invillia.meubeats.domain.usecase

import com.invillia.meubeats.domain.repository.BeatsRepository
import io.mockk.impl.annotations.MockK

class GetNetworkHeadphonesUseCaseTest {

    @MockK
    private lateinit var repository: BeatsRepository

    /*@InjectMockKs
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
    }*/

}