package com.deepakbarad.weatherapp.framework.services

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.deepakbarad.weatherapp.framework.di.ServiceModule
import timber.log.Timber
import javax.inject.Inject

interface ILocationService {
    fun getLocation(provider: String): Location?
    fun startTracking()
    fun stopTracking()
}

class LocationService @Inject constructor(
    private val context: Context,
    @ServiceModule.LocationListenerQualifier val locationListener: LocationListener
) : ILocationService {
    private var TAG: String = "LocationService"
    private var REFRESH_TIME: Long = 400
    private var REFRESH_DISTANCE: Float = 1.00f

    private val locManager: LocationManager
        get() = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private fun getLocationManager(): LocationManager {
        return locManager
    }

    override fun getLocation(provider: String): Location? {
        try {
            return getLocationManager().getLastKnownLocation(provider)
        } catch (exception: SecurityException) {
            Timber.tag(TAG).e("getLocation -> %s", exception.message!!)
        }
        return null
    }

    override fun startTracking() {
        try {
            getLocationManager().requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                REFRESH_TIME,
                REFRESH_DISTANCE,
                locationListener
            )
            getLocationManager().requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                REFRESH_TIME,
                REFRESH_DISTANCE,
                locationListener
            )

        } catch (exception: SecurityException) {
            Timber.tag(TAG).e("startTracking -> %s", exception.message!!)
        }
    }

    override fun stopTracking() {
        getLocationManager().removeUpdates(locationListener)
    }
}