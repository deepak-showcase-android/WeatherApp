package com.deepakbarad.weatherapp.core.usecase

import com.deepakbarad.weatherapp.core.data.CurrentWeather
import com.deepakbarad.weatherapp.core.repository.OpenWeatherRepository

class SaveWeather(private val openWeatherRepository: OpenWeatherRepository) {
    suspend operator fun invoke(currentWeather: CurrentWeather) =
        openWeatherRepository.saveForecast(currentWeather)
}