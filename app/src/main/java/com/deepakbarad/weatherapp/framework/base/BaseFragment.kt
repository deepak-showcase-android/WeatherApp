package com.deepakbarad.weatherapp.framework.base

import androidx.fragment.app.Fragment
import com.deepakbarad.weatherapp.framework.services.LocationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseFragment : Fragment() {
    open fun setObservers() {}
    open fun setListeners() {}

    @Inject
    lateinit var locationService: LocationService
}