package com.invillia.meubeats.presentation.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.R
import com.invillia.meubeats.presentation.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class ProductListScreenTest {

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun should_NavigateToDetailScreen_WhenClickedOnRecyclerListItem() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        val productListScenario =
            launchFragmentInContainer<ProductListFragment>(themeResId = R.style.Theme_MeuBeats)

        productListScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.navigation)
            navController.setCurrentDestination(R.id.productListFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.rv_products)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_products)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.productDetailsFragment)
    }
}