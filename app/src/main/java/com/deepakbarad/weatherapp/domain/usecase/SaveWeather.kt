package com.deepakbarad.weatherapp.domain.usecase

import com.deepakbarad.weatherapp.domain.model.CurrentWeather

class SaveWeather(private val openWeatherRepository: com.deepakbarad.weatherapp.data.repository.OpenWeatherRepository) {
    suspend operator fun invoke(currentWeather: CurrentWeather) =
        openWeatherRepository.saveForecast(currentWeather)
}