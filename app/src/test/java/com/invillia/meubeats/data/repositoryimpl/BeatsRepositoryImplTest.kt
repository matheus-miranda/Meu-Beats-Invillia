package com.invillia.meubeats.data.repositoryimpl

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.data.mapper.NetworkHeadphoneMapper
import com.invillia.meubeats.data.remote.api.BeatsApi
import com.invillia.meubeats.util.CoroutineTestRule
import com.invillia.meubeats.util.TestData.HEADPHONE
import com.invillia.meubeats.util.TestData.NETWORK_HEADPHONE
import com.invillia.meubeats.util.TestData.initMockkAnnotations
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.manipulation.Ordering

@ExperimentalCoroutinesApi
class BeatsRepositoryImplTest {

    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @InjectMockKs
    private lateinit var repositoryImpl: BeatsRepositoryImpl

    @MockK
    private lateinit var service: BeatsApi

    @MockK
    private lateinit var mapper: NetworkHeadphoneMapper

    @RelaxedMockK
    private lateinit var context: Context

    @Before
    fun setUp() {
        clearAllMocks()
        initMockkAnnotations()
    }

    @Test
    fun getNetworkHeadphones() = runBlockingTest {
        val entityHeadphoneList = listOf(NETWORK_HEADPHONE)
        val expectedResult = flowOf(listOf(HEADPHONE))

        coEvery { service.getHeadphones() } returns listOf(NETWORK_HEADPHONE)
        every { mapper.toDomainList(entityHeadphoneList) } returns listOf(HEADPHONE)
        val result = repositoryImpl.getNetworkHeadphones().first()

        verify(exactly = 1) { mapper.toDomainList(entityHeadphoneList) }
        coVerify(exactly = 1) { service.getHeadphones() }
        assertThat(result).isEqualTo(expectedResult.first())
    }
}