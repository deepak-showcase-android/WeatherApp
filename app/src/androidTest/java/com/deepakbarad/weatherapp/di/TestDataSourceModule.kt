package com.deepakbarad.weatherapp.di

import android.content.Context
import com.deepakbarad.weatherapp.framework.data.datasources.remote.OpenWeatherRemoteDataSource
import com.deepakbarad.weatherapp.framework.data.interfaces.IOpenWeatherDataSource
import com.deepakbarad.weatherapp.framework.network.IOpenWeatherApi
import com.deepakbarad.weatherapp.network.TestRetrofitHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideOpenWeatherRemoteDataSource(@ApplicationContext appContext: Context): OpenWeatherRemoteDataSource =
        OpenWeatherRemoteDataSource(
            TestRetrofitHelper.getInstance().create(IOpenWeatherApi::class.java)
        )
}

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderRemoteDataSourceModule {
    @OpenWeatherRemoteDataSourceQualifier
    @Singleton
    @Binds
    abstract fun bindOpenWeatherRemoteDataSource(impl: OpenWeatherRemoteDataSource): IOpenWeatherDataSource
}

@Qualifier
annotation class OpenWeatherRemoteDataSourceQualifier