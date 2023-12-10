package com.deepakbarad.weatherapp.domain.usecase

import com.deepakbarad.weatherapp.domain.usecase.GetCachedCurrentWeather
import com.deepakbarad.weatherapp.domain.usecase.GetWeather
import com.deepakbarad.weatherapp.domain.usecase.SaveWeather

data class UseCases(
    val getWeather: GetWeather,
    val saveWeather: SaveWeather,
    val getCachedCurrentWeather: GetCachedCurrentWeather
)