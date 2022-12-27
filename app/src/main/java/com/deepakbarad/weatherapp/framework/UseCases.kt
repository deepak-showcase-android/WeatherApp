package com.deepakbarad.weatherapp.framework

import com.deepakbarad.weatherapp.core.usecase.GetCachedCurrentWeather
import com.deepakbarad.weatherapp.core.usecase.GetWeather
import com.deepakbarad.weatherapp.core.usecase.SaveWeather

data class UseCases(
    val getWeather: GetWeather,
    val saveWeather: SaveWeather,
    val getCachedCurrentWeather: GetCachedCurrentWeather
)