package com.deepakbarad.weatherapp.activityTests

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.deepakbarad.weatherapp.R
import com.deepakbarad.weatherapp.framework.di.ApplicationModule
import com.deepakbarad.weatherapp.framework.di.DataSourceModule
import com.deepakbarad.weatherapp.framework.di.NetworkModule
import com.deepakbarad.weatherapp.framework.di.ServiceModule
import com.deepakbarad.weatherapp.framework.utils.EspressoIdlingResource.countingIdlingResource
import com.deepakbarad.weatherapp.presentation.acitivites.MainActivity
import com.deepakbarad.weatherapp.utils.DataBindingIdlingResource
import com.deepakbarad.weatherapp.utils.monitorActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(
    ApplicationModule::class,
    DataSourceModule::class,
    NetworkModule::class,
    ServiceModule::class
)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var mockServer: MockWebServer
    private val idlingResourceRegistry = IdlingRegistry.getInstance()
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun setUp() {
        try {
            mockServer = MockWebServer()
            mockServer.start(8080)
        } catch (exception: Exception) {
            println(exception)
        }
        idlingResourceRegistry.register(countingIdlingResource)
        idlingResourceRegistry.register(dataBindingIdlingResource)
        /*
        val activityScenario: ActivityScenario<*> = ActivityScenario.launch(
            MainActivity::class.java
        )
        activityScenario.onActivity {
            idlingResourceRegistry.register(countingIdlingResource)
            idlingResourceRegistry.register(dataBindingIdlingResource)
        }
        */
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
        idlingResourceRegistry.resources.forEach {
            idlingResourceRegistry.unregister(it)
        }
    }

    @Test
    fun display_weather_info_success_test(): Unit = runBlocking {
        mockServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val mockJson =
                    com.deepakbarad.weatherapp.utils.FileReader.readStringFromFile("weather_response.json")
                val mockResponse = MockResponse()
                    .setResponseCode(200)
                    .setBody(mockJson)
                return mockResponse
            }
        }
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        onView(withId(R.id.navigation_weather)).perform(click())
        onView(allOf(withId(R.id.tvCity), isCompletelyDisplayed()))
        onView(allOf(withId(R.id.tvCity))).check(matches(withText("Test Location")))
        activityScenario.close()
    }
}