package com.deepakbarad.weatherapp.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepakbarad.weatherapp.data.repository.OpenWeatherRepository
import com.deepakbarad.weatherapp.model.CurrentWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val openWeatherRepository: OpenWeatherRepository) :
    ViewModel() {

    val loadingFlag = ObservableField<Boolean>()
    val loadingMessage = ObservableField<String>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.d(throwable)
        loadingFlag.set(false)
    }

    private val mCurrentWeather: MutableLiveData<CurrentWeather> = MutableLiveData()
    val currentWeather: LiveData<CurrentWeather> get() = mCurrentWeather

    fun getForecast5(longitude: Double, latitude: Double) {
        loadingMessage.set("Fetching weather forecast...")
        loadingFlag.set(true)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            mCurrentWeather.postValue(openWeatherRepository.getForecast5(longitude, latitude))
            loadingFlag.set(false)
        }
    }
}