package com.deepakbarad.weatherapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.deepakbarad.weatherapp.databinding.FragmentHomeBinding
import com.deepakbarad.weatherapp.services.LocationService
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var locationService: LocationService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = homeViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        locationService.locationListener.currentLocation?.longitude?.let { longitude ->
            locationService.locationListener.currentLocation?.latitude?.let { latitude ->
                homeViewModel.getForecast5(
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
        homeViewModel.currentWeather.observe(viewLifecycleOwner) { currentWeather ->
            Timber.i("CurrentWeather ->", currentWeather)
        }
    }

    override fun onResume() {
        super.onResume()
        locationService.startTracking()
    }

    override fun onStop() {
        super.onStop()
        locationService.stopTracking()
    }
}