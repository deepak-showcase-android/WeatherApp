package com.deepakbarad.weatherapp.core.usecase

import com.deepakbarad.weatherapp.core.repository.OpenWeatherRepository

class GetCachedCurrentWeather(private val openWeatherRepository: OpenWeatherRepository) {
    suspend operator fun invoke() = openWeatherRepository.getCachedForecastFlow()
}