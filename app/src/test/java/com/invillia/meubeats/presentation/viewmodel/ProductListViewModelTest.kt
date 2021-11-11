package com.invillia.meubeats.presentation.viewmodel

import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.domain.model.Headphone
import com.invillia.meubeats.domain.usecase.GetNetworkHeadphonesUseCase
import com.invillia.meubeats.util.CoroutineTestRule
import com.invillia.meubeats.util.TestData.HEADPHONE
import com.invillia.meubeats.util.TestData.initMockkAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductListViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @InjectMockKs
    private lateinit var viewModel: ProductListViewModel

    @RelaxedMockK
    private lateinit var getNetworkHeadphones: GetNetworkHeadphonesUseCase

    @Before
    fun setUp() = initMockkAnnotations()

    @Test
    fun `when product is clicked, navigateToProductDetails value is updated`() {
        viewModel.onProductClicked(HEADPHONE)

        assertThat(viewModel.navigateToProductDetails.value).isInstanceOf(Headphone::class.java)
    }

    @Test
    fun `when navigation is complete, set navigateToProductDetails value to null `() {
        val expectedValue = null

        viewModel.doneNavigatingToProductDetails()

        assertThat(viewModel.navigateToProductDetails.value).isEqualTo(expectedValue)
    }
}