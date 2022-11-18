package com.deepakbarad.weatherapp.framework.data.repository

import com.deepakbarad.weatherapp.framework.data.interfaces.IOpenWeatherDataSource
import com.deepakbarad.weatherapp.framework.di.OpenWeatherRemoteDataSourceQualifier
import com.deepakbarad.weatherapp.framework.model.CurrentWeather
import javax.inject.Inject

class OpenWeatherRepository @Inject constructor(@OpenWeatherRemoteDataSourceQualifier private val openWeatherRemoteDataSource: IOpenWeatherDataSource) :
    IOpenWeatherDataSource {

    override suspend fun getForecast5(longitude: Double, latitude: Double): CurrentWeather {
        return openWeatherRemoteDataSource.getForecast5(longitude, latitude)
    }
}