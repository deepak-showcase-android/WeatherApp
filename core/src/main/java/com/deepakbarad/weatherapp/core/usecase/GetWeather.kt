package com.deepakbarad.weatherapp.core.usecase

import com.deepakbarad.weatherapp.core.repository.OpenWeatherRepository

class GetWeather(private val openWeatherRepository: OpenWeatherRepository) {
    suspend operator fun invoke(longitude: Double, latitude: Double) =
        openWeatherRepository.getForecast5(longitude, latitude)
}