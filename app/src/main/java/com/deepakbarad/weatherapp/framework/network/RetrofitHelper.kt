package com.deepakbarad.weatherapp.framework.network

import com.google.gson.GsonBuilder
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val baseUrl = "https://api.openweathermap.org"
    private const val basePattern = "*.openweathermap.org"
    private val gson = GsonBuilder().setLenient().create()

    fun getInstance(): Retrofit {
        val certificatePinner = CertificatePinner.Builder()
            .add(basePattern, "sha256/axmGTWYycVN5oCjh3GJrxWVndLSZjypDO6evrHMwbXg=").build()
        val okHttpClient = OkHttpClient.Builder().certificatePinner(certificatePinner).build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}