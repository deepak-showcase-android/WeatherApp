package com.deepakbarad.weatherapp.core.data

data class CurrentWeather(
    var id: Int = 0,
    var cod: String = "",
    var message: Int = 0,
    var cnt: Int = 0,
    var list: List<ListData> = emptyList(),
    var city: City? = null,
    var collectedTime: Long = 0
)