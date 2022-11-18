package com.deepakbarad.weatherapp.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "https://api.openweathermap.org"
    //val lat = "12.972442"
    //val long = "77.580643"
    //val apiKey = "d242ceb4ca60d98d2385dc641f549b22"
    //val baseUrl = "http://api.openweathermap.org/data/2.5/forecast?lat=$lat&lon=$long&appid=$apiKey"

    //"api.openweathermap.org/data/2.5/forecast?lat=12.972442&lon=77.580643&appid=d242ceb4ca60d98d2385dc641f549b22"

    val gson = GsonBuilder().setLenient().create()

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}