package com.deepakbarad.weatherapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.deepakbarad.weatherapp.databinding.FragmentWeatherBinding
import com.deepakbarad.weatherapp.framework.base.BaseFragment
import com.deepakbarad.weatherapp.framework.model.CurrentWeather
import com.deepakbarad.weatherapp.framework.services.LocationListenerService
import com.deepakbarad.weatherapp.framework.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class WeatherFragment : BaseFragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        binding.viewModel = weatherViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
        getForecastWithFlow()
    }

    private fun getForecastWithFlow() {
        val locationListenerService = locationService.locationListener as LocationListenerService
        locationListenerService.currentLocation?.longitude?.let { longitude ->
            locationListenerService.currentLocation?.latitude?.let { latitude ->
                lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        weatherViewModel.getForecast5Flow(longitude, latitude)
                            .collect { currentWeather ->
                                delay(1000L)
                                Timber.i("Collected CurrentWeather ->", currentWeather)
                                displayWeatherInfo(currentWeather)
                            }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setListeners() {
        super.setListeners()
        binding.fabRefresh.setOnClickListener {
            binding.tvWeather.text = ""
            getForecastWithFlow()
        }
    }

    override fun setObservers() {
        super.setObservers()
        weatherViewModel.errorInfo.observe(viewLifecycleOwner) { errorMessage ->
            binding.root.showSnackbar(binding.root, errorMessage, Snackbar.LENGTH_SHORT, null) {}
        }
    }

    private fun displayWeatherInfo(currentWeather: CurrentWeather) {
        val weatherData = StringBuilder()
        with(currentWeather) {
            binding.tvCollectionTime.text = this.collectedTime?.toString()
            binding.tvCity.text = this.city?.name
            this.list?.forEach { listItem ->
                weatherData.appendLine("${listItem.weather?.get(0)?.main}(${listItem.weather?.get(0)?.description}) On ${listItem.dtTxt}")
            }
            binding.tvWeather.text = weatherData.toString()
            (locationService.locationListener as LocationListenerService).currentLocation?.let { location ->
                binding.tvCoordinates.text = buildString {
                    append(location.longitude)
                    append(" ")
                    append(location.latitude)
                }
            }
            weatherViewModel.loadingFlag.set(false)
        }
    }
}