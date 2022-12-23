package com.deepakbarad.weatherapp.ui.weather

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.deepakbarad.weatherapp.R
import com.deepakbarad.weatherapp.framework.base.BaseViewModel
import com.deepakbarad.weatherapp.framework.data.repository.OpenWeatherRepository
import com.deepakbarad.weatherapp.framework.model.City
import com.deepakbarad.weatherapp.framework.model.CurrentWeather
import com.deepakbarad.weatherapp.framework.utils.EspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val context: Application,
    private val openWeatherRepository: OpenWeatherRepository
) : BaseViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.d(throwable)
        loadingFlag.set(false)
        mErrorInfo.postValue(context.getString(R.string.no_weather_info))

        //SSL pinning used...if SHA256 entered in network_security_config.xml is wrong...exception will be thrown.
        //https://sreekumar-av.medium.com/certificate-public-key-pinning-in-android-using-retrofit-2-0-74140800025b
    }

    suspend fun getForecast5Flow(longitude: Double, latitude: Double): Flow<CurrentWeather> {
        return openWeatherRepository.getForecast5(longitude, latitude)
            .onStart {
                loadingFlag.set(true)
                EspressoIdlingResource.increment()
            }.onCompletion { cause: Throwable? ->
                when (cause) {
                    null -> {
                        Timber.d("Flow completed successfully")
                        EspressoIdlingResource.decrement()
                    }
                    is Exception -> {
                        Timber.d("cause is Exception" + cause)
                    }
                    else -> {
                        Timber.d(cause)
                    }
                }
                loadingFlag.set(false)
            }.catch { throwable ->
                Timber.d(throwable)
                mErrorInfo.postValue(context.getString(R.string.no_weather_info))
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), CurrentWeather().apply {
                this.collectedTime = 0L
                this.city = City().apply {
                    this.name = "Initial City"
                }
            })
    }
}