package com.deepakbarad.weatherapp.data.repository

import com.deepakbarad.weatherapp.data.OpenWeatherRemoteDataSource
import com.deepakbarad.weatherapp.data.interfaces.IOpenWeatherDataSource
import com.deepakbarad.weatherapp.di.OpenWeatherRemoteDataSourceQualifier
import com.deepakbarad.weatherapp.model.CurrentWeather
import javax.inject.Inject

class OpenWeatherRepository @Inject constructor(@OpenWeatherRemoteDataSourceQualifier private val openWeatherRemoteDataSource: IOpenWeatherDataSource) :
    IOpenWeatherDataSource {

    override suspend fun getForecast5(longitude: Double, latitude: Double): CurrentWeather {
        return openWeatherRemoteDataSource.getForecast5(longitude, latitude)
    }
}