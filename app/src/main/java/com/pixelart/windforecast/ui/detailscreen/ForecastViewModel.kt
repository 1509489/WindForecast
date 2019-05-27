package com.pixelart.windforecast.ui.detailscreen

import androidx.lifecycle.ViewModel
import com.pixelart.windforecast.data.repository.ForecastRepositoryImpl

class ForecastViewModel(private val repository: ForecastRepositoryImpl): ViewModel(){

    fun setWindForecast(locationName: String) = repository.getForecast(locationName)
    fun setCurrentWind(locationName: String) = repository.getCurrentWind(locationName)
    fun setMessage() = repository.showMessage()

    override fun onCleared() {
        super.onCleared()
        repository.dispose()
    }
}