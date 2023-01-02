package com.deepakbarad.weatherapp.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestRetrofitHelper {

    private const val baseUrl = "http://localhost:8080/"
    private const val basePattern = "*.openweathermap.org"
    private val gson = GsonBuilder().setLenient().create()

    fun getInstance(): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }
}