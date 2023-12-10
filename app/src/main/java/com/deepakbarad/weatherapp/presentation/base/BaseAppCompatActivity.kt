package com.deepakbarad.weatherapp.presentation.base

import androidx.appcompat.app.AppCompatActivity
import com.deepakbarad.weatherapp.core.services.LocationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseAppCompatActivity : AppCompatActivity() {

    @Inject
    lateinit var locationService: LocationService
}