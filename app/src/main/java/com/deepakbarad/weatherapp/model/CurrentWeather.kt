package com.deepakbarad.weatherapp.model

class City {
    var id: Int? = null
    var name: String? = null
    var coord: Coord? = null
    var country: String? = null
    var population: Int? = null
    var timezone: Int? = null
    var sunrise: Int? = null
    var sunset: Int? = null
}

class Clouds {
    var all: Int? = null
}

class Coord {
    var lat: Double? = null
    var lon: Double? = null
}

class ListData {
    var dt: Int? = null
    var main: Main? = null
    var weather: kotlin.collections.List<Weather>? = null
    var clouds: Clouds? = null
    var wind: Wind? = null
    var visibility: Int? = null
    var pop: Double? = null
    var sys: Sys? = null
    var dtTxt: String? = null
    var rain: Rain? = null
}

class Main {
    var temp: Double? = null
    var feelsLike: Double? = null
    var tempMin: Double? = null
    var tempMax: Double? = null
    var pressure: Int? = null
    var seaLevel: Int? = null
    var grndLevel: Int? = null
    var humidity: Int? = null
    var tempKf: Int? = null
}

class Rain {
    var _3h: Double? = null
}

class Sys {
    var pod: String? = null
}

class Weather {
    var id: Int? = null
    var main: String? = null
    var description: String? = null
    var icon: String? = null
}

class CurrentWeather {
    var cod: String? = null
    var message: Int? = null
    var cnt: Int? = null
    var list: List<ListData>? = null
    var city: City? = null
}

class Wind {
    var speed: Double? = null
    var deg: Int? = null
    var gust: Double? = null
}
