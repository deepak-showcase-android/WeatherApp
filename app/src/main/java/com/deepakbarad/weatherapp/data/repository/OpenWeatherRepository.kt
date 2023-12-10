package com.deepakbarad.weatherapp.data.repository

import com.deepakbarad.weatherapp.domain.model.CurrentWeather
import com.deepakbarad.weatherapp.core.di.OpenWeatherLocalDataSourceQualifier
import com.deepakbarad.weatherapp.core.di.OpenWeatherRemoteDataSourceQualifier
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpenWeatherRepository @Inject constructor(
    @OpenWeatherRemoteDataSourceQualifier private val openWeatherRemoteDataSource: IOpenWeatherDataSource,
    @OpenWeatherLocalDataSourceQualifier private val openWeatherLocalDataSource: IOpenWeatherDataSource
) :
    IOpenWeatherDataSource {

    override suspend fun getForecast5(longitude: Double, latitude: Double): Flow<CurrentWeather> {
        return openWeatherRemoteDataSource.getForecast5(longitude, latitude)
    }

    override suspend fun getCachedForecast(): CurrentWeather? {
        return openWeatherLocalDataSource.getCachedForecast()
    }

    override suspend fun saveForecast(currentWeather: CurrentWeather) {
        openWeatherLocalDataSource.saveForecast(currentWeather)
    }

    override suspend fun getCachedForecastFlow(): Flow<CurrentWeather?> {
        return openWeatherLocalDataSource.getCachedForecastFlow()
    }
}