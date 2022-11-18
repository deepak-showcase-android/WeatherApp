package com.deepakbarad.weatherapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.deepakbarad.weatherapp.databinding.FragmentWeatherBinding
import com.deepakbarad.weatherapp.model.CurrentWeather
import com.deepakbarad.weatherapp.services.LocationService
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel: WeatherViewModel by viewModels()

    @Inject
    lateinit var locationService: LocationService

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
        setObservers()
        getForecast()
    }

    private fun getForecast() {
        locationService.locationListener.currentLocation?.longitude?.let { longitude ->
            locationService.locationListener.currentLocation?.latitude?.let { latitude ->
                weatherViewModel.getForecast5(
                    longitude, latitude
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers() {
        weatherViewModel.currentWeather.observe(viewLifecycleOwner) { currentWeather ->
            Timber.i("CurrentWeather ->", currentWeather)
            displayWeatherInfo(currentWeather)
        }
    }

    private fun displayWeatherInfo(currentWeather: CurrentWeather) {
        val weatherData = StringBuilder()
        with(currentWeather) {
            binding.tvCity.text = this.city?.name
            this.list?.forEach { listItem ->
                weatherData.appendLine(listItem.weather?.get(0)?.main + " On " + listItem.dtTxt)
            }
            binding.tvWeather.text = weatherData.toString()
        }
    }


}