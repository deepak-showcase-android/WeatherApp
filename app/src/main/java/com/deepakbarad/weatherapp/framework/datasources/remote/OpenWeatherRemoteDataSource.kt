package com.deepakbarad.weatherapp.framework.datasources.remote

import com.deepakbarad.weatherapp.BuildConfig.OPEN_WEATHER_API_KEY
import com.deepakbarad.weatherapp.core.data.CurrentWeather
import com.deepakbarad.weatherapp.core.di.OpenWeatherApiQualifier
import com.deepakbarad.weatherapp.core.repository.IOpenWeatherDataSource
import com.deepakbarad.weatherapp.framework.network.IOpenWeatherApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class OpenWeatherRemoteDataSource @Inject constructor(
    @OpenWeatherApiQualifier private val openWeatherApi: IOpenWeatherApi
) : IOpenWeatherDataSource {

    override suspend fun getForecast5(longitude: Double, latitude: Double): Flow<CurrentWeather> =
        flow {
            try {
                val queryMap: MutableMap<String, String> = mutableMapOf()
                queryMap["lat"] = latitude.toString()
                queryMap["lon"] = longitude.toString()
                queryMap["appid"] = OPEN_WEATHER_API_KEY
                val currentWeatherResponse = openWeatherApi.getForecast5(queryMap)
                val currentWeather = currentWeatherResponse.body()
                println(currentWeather?.message)
                if (currentWeather != null) {
                    currentWeather.collectedTime = System.currentTimeMillis()
                    emit(currentWeather)
                } else {
                    Timber.d("exception!!")
                    throw(Exception("No current weather info available"))
                }
            } catch (exception: Exception) {
                println(exception)
            }
        }

    override suspend fun getCachedForecast(): CurrentWeather? {
        throw IllegalArgumentException("not to be implemented here")
    }

    override suspend fun saveForecast(currentWeather: CurrentWeather) {
        throw IllegalArgumentException("not to be implemented here")
    }
}