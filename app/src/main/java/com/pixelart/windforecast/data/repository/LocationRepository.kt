package com.pixelart.windforecast.data.repository

import androidx.lifecycle.LiveData
import com.pixelart.windforecast.data.entities.LocationEntity

interface LocationRepository {
    //fun addLocations(location: LocationEntity)
    fun getLocations():LiveData<List<LocationEntity>>
    fun getLocationNetwork(locationName: String)
    fun dispose()
    fun getErrorMessage():LiveData<String>
}