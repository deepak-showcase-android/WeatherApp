package com.deepakbarad.weatherapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.deepakbarad.weatherapp.framework.di.ContextModule
import com.deepakbarad.weatherapp.framework.di.DataSourceModule
import com.deepakbarad.weatherapp.framework.di.NetworkModule
import com.deepakbarad.weatherapp.framework.di.ServiceModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
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
    ContextModule::class,
    DataSourceModule::class,
    NetworkModule::class,
    ServiceModule::class
)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var mockServer: MockWebServer

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @After
    fun tearDown() = mockServer.shutdown()

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
        activityRule.launchActivity(null)
        onView(withId(R.id.navigation_weather)).perform(click())
        //onView(withId(R.id.tvCity))
        onView(allOf(withId(R.id.tvCity), isCompletelyDisplayed()))
        repeat(5) {
            delay(5000L)
            onView(allOf(withId(R.id.tvCity))).check(matches(withText("Test Location")))
        }
    }
}