package com.deepakbarad.weatherapp.framework.base

import androidx.appcompat.app.AppCompatActivity
import com.deepakbarad.weatherapp.framework.services.LocationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseAppCompatActivity : AppCompatActivity() {

    @Inject
    lateinit var locationService: LocationService
}