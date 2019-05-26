package com.pixelart.windforecast.ui.locationscreen

import androidx.lifecycle.ViewModel
import com.pixelart.windforecast.data.repository.LocationRepositoryImpl

class LocationViewModel(private val repository: LocationRepositoryImpl):ViewModel() {

    fun getLocations() = repository.getLocations()
    fun addLocation(location: String) = repository.getLocationNetwork(location)
    fun showErrorMessage() = repository.getErrorMessage()

    override fun onCleared() {
        super.onCleared()
        repository.dispose()
    }
}