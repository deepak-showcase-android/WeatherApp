package com.deepakbarad.weatherapp.framework.services

import android.content.Context
import android.location.Location
import android.location.LocationManager
import timber.log.Timber
import javax.inject.Inject

class LocationService @Inject constructor(
    private val context: Context,
    val locationListener: LocationListener
) {
    private var TAG: String = "LocationService"
    private var REFRESH_TIME: Long = 400
    private var REFRESH_DISTANCE: Float = 1.00f

    private val locManager: LocationManager
        get() = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private fun getLocationManager(): LocationManager {
        return locManager
    }

    fun getLocation(provider: String): Location? {
        try {
            return getLocationManager().getLastKnownLocation(provider)
        } catch (exception: SecurityException) {
            Timber.tag(TAG).e("getLocation -> %s", exception.message!!)
        }
        return null
    }

    fun startTracking() {
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

    fun stopTracking() {
        getLocationManager().removeUpdates(locationListener)
    }
}