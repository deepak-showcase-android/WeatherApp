package com.deepakbarad.weatherapp.domain.model

data class Main(
    var temp: Double? = null,
    var feelsLike: Double? = null,
    var tempMin: Double? = null,
    var tempMax: Double? = null,
    var pressure: Int? = null,
    var seaLevel: Int? = null,
    var grndLevel: Int? = null,
    var humidity: Int? = null,
    var tempKf: Int? = null
)
