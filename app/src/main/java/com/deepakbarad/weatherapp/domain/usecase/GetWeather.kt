package com.deepakbarad.weatherapp.domain.usecase

import com.deepakbarad.weatherapp.data.repository.OpenWeatherRepository

class GetWeather(private val openWeatherRepository: com.deepakbarad.weatherapp.data.repository.OpenWeatherRepository) {
    suspend operator fun invoke(longitude: Double, latitude: Double) =
        openWeatherRepository.getForecast5(longitude, latitude)
}