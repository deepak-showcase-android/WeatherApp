package com.deepakbarad.weatherapp.framework.idlingresource

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResourceCounter {
    private const val IDLING_RESOURCE_NAME = "GlobalIdlingResourceCounter"
    val countingIdlingResource = CountingIdlingResource(IDLING_RESOURCE_NAME)
}