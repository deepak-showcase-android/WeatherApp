package com.deepakbarad.weatherapp.core.repository

import com.deepakbarad.weatherapp.core.data.CurrentWeather
import com.deepakbarad.weatherapp.core.di.OpenWeatherRemoteDataSourceQualifier
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpenWeatherRepository @Inject constructor(@OpenWeatherRemoteDataSourceQualifier private val openWeatherRemoteDataSource: IOpenWeatherDataSource) :
    IOpenWeatherDataSource {

    override suspend fun getForecast5(longitude: Double, latitude: Double): Flow<CurrentWeather> {
        return openWeatherRemoteDataSource.getForecast5(longitude, latitude)
    }

    override suspend fun getCachedForecast(): CurrentWeather? {
        TODO("Not yet implemented")
    }

    override suspend fun saveForecast(currentWeather: CurrentWeather) {
        TODO("Not yet implemented")
    }
}