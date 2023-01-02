package com.deepakbarad.weatherapp.core.data

data class City(
    var id: Int? = null,
    var name: String? = null,
    var coord: Coord? = null,
    var country: String? = null,
    var population: Int? = null,
    var timezone: Int? = null,
    var sunrise: Int? = null,
    var sunset: Int? = null
)
