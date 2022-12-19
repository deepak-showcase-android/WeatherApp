package com.deepakbarad.weatherapp.ui.weather

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deepakbarad.weatherapp.R
import com.deepakbarad.weatherapp.framework.base.BaseViewModel
import com.deepakbarad.weatherapp.framework.data.repository.OpenWeatherRepository
import com.deepakbarad.weatherapp.framework.model.CurrentWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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

    private val mCurrentWeather: MutableLiveData<CurrentWeather> = MutableLiveData()
    val currentWeather: LiveData<CurrentWeather> get() = mCurrentWeather

    fun getForecast5(longitude: Double, latitude: Double) {
        loadingMessage.set(context.getString(R.string.fetching_weather_forecast))
        loadingFlag.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            openWeatherRepository.getForecast5(longitude, latitude)
                .onCompletion { cause: Throwable? ->
                    when (cause) {
                        null -> {
                            Timber.d("Flow completed successfully")
                        }
                        is Exception -> {
                            Timber.d("cause is Exception")
                        }
                        else -> {
                            Timber.d(cause)
                        }
                    }
                    loadingFlag.set(false)
                }.onEach {
                    mCurrentWeather.postValue(it)
                }.catch { throwable ->
                    Timber.d(throwable)
                    mErrorInfo.postValue(context.getString(R.string.no_weather_info))
                }.launchIn(this)
        }
    }
}