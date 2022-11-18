package com.deepakbarad.weatherapp.ui.weather

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepakbarad.weatherapp.R
import com.deepakbarad.weatherapp.data.repository.OpenWeatherRepository
import com.deepakbarad.weatherapp.model.CurrentWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val context: Application,
    private val openWeatherRepository: OpenWeatherRepository
) : ViewModel() {

    val loadingFlag = ObservableField<Boolean>()
    val loadingMessage = ObservableField<String>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.d(throwable)
        loadingFlag.set(false)
    }

    private val mCurrentWeather: MutableLiveData<CurrentWeather> = MutableLiveData()
    val currentWeather: LiveData<CurrentWeather> get() = mCurrentWeather

    fun getForecast5(longitude: Double, latitude: Double) {
        loadingMessage.set(context.getString(R.string.fetching_weather_forecast))
        loadingFlag.set(true)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            mCurrentWeather.postValue(openWeatherRepository.getForecast5(longitude, latitude))
            loadingFlag.set(false)
        }
    }
}