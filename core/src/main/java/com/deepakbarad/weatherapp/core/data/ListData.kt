package com.deepakbarad.weatherapp.core.data

import com.google.gson.annotations.SerializedName

data class ListData(
    var dt: Int? = null,
    var main: Main? = null,
    var weather: kotlin.collections.List<Weather>? = null,
    var clouds: Clouds? = null,
    var wind: Wind? = null,
    var visibility: Int? = null,
    var pop: Double? = null,
    var sys: Sys? = null,
    @SerializedName("dt_txt")
    var dtTxt: String? = null,
    var rain: Rain? = null
)
