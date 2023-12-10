package com.deepakbarad.weatherapp.domain.usecase

import com.deepakbarad.weatherapp.data.repository.OpenWeatherRepository

class GetCachedCurrentWeather(private val openWeatherRepository: com.deepakbarad.weatherapp.data.repository.OpenWeatherRepository) {
    suspend operator fun invoke() = openWeatherRepository.getCachedForecastFlow()
}