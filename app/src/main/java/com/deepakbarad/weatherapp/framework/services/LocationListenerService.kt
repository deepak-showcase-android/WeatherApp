package com.deepakbarad.weatherapp.framework.services

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import javax.inject.Inject

class LocationListenerService @Inject constructor() : LocationListener {

    private var currLoc: Location? = null;
    val currentLocation: Location? get() = currLoc

    override fun onLocationChanged(location: Location) {
        this.currLoc = location;
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        //not to be implemented now
    }

    override fun onProviderEnabled(p0: String) {
        //not to be implemented now
    }

    override fun onProviderDisabled(p0: String) {
        //not to be implemented now
    }
}