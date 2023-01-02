package com.deepakbarad.weatherapp.framework.viewmodels

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.deepakbarad.weatherapp.R
import com.deepakbarad.weatherapp.core.data.City
import com.deepakbarad.weatherapp.core.data.CurrentWeather
import com.deepakbarad.weatherapp.framework.UseCases
import com.deepakbarad.weatherapp.framework.base.BaseViewModel
import com.deepakbarad.weatherapp.framework.utils.EspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val context: Application,
    private val useCases: UseCases
) : BaseViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var cachedCurrentWeather: CurrentWeather? = null

    fun getCachedCurrentWeather() {
        coroutineScope.launch {
            useCases.getCachedCurrentWeather().collect {
                cachedCurrentWeather = it
                println("Update -> collected cached current weather")
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.d(throwable)
        loadingFlag.set(false)
        mErrorInfo.postValue(context.getString(R.string.no_weather_info))

        //SSL pinning used...if SHA256 entered in network_security_config.xml is wrong...exception will be thrown.
        //https://sreekumar-av.medium.com/certificate-public-key-pinning-in-android-using-retrofit-2-0-74140800025b
    }

    suspend fun getForecast5Flow(longitude: Double, latitude: Double): Flow<CurrentWeather> {
        return useCases.getWeather(longitude, latitude)
            .onStart {
                EspressoIdlingResource.increment()
                loadingFlag.set(true)
            }.onCompletion { cause: Throwable? ->
                when (cause) {
                    null -> {
                        Timber.d("Flow completed successfully")
                    }
                    is TimeoutCancellationException -> {
                        mErrorInfo.postValue(context.getString(R.string.unable_to_get_result_within_time))
                    }
                    is Exception -> {
                        Timber.d("cause is Exception" + cause)
                        mErrorInfo.postValue(context.getString(R.string.no_weather_info))
                    }
                    else -> {
                        Timber.d(cause)
                    }
                }
                loadingFlag.set(false)
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), CurrentWeather().apply {
                this.collectedTime = 0L
                this.city = City().apply {
                    this.name = "Initial City"
                }
            })
    }

    fun saveCurrentWeather(currentWeather: CurrentWeather) {
        cachedCurrentWeather?.let {
            currentWeather.id = it.id
        }.run {
            cachedCurrentWeather = currentWeather
        }
        coroutineScope.launch {
            useCases.saveWeather(currentWeather)
        }
    }
}