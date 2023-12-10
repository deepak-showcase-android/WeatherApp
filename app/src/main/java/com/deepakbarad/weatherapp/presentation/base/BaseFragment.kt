package com.deepakbarad.weatherapp.presentation.base

import androidx.fragment.app.Fragment
import com.deepakbarad.weatherapp.core.services.LocationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseFragment : Fragment() {
    open fun setObservers() {}
    open fun setListeners() {}

    @Inject
    lateinit var locationService: LocationService
}