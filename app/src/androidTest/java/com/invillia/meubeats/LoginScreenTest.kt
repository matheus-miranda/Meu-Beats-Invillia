package com.invillia.meubeats

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth.assertThat
import com.invillia.meubeats.presentation.ui.LoginFragment
import org.junit.Test

class LoginScreenTest {

    @Test
    fun testNavigateToProductListScreen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        // Create a graphical FragmentScenario for the LoginScreen
        val loginScenario =
            launchFragmentInContainer<LoginFragment>(themeResId = R.style.Theme_MeuBeats)

        loginScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.navigation)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.productListFragment)
    }
}