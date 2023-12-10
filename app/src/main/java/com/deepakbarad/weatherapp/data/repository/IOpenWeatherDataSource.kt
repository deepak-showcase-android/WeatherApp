package com.deepakbarad.weatherapp.data.repository

import com.deepakbarad.weatherapp.domain.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface IOpenWeatherDataSource {
    suspend fun getForecast5(longitude: Double, latitude: Double): Flow<CurrentWeather>
    suspend fun saveForecast(currentWeather: CurrentWeather)
    suspend fun getCachedForecast(): CurrentWeather?
    suspend fun getCachedForecastFlow(): Flow<CurrentWeather?>
}