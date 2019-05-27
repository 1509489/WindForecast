package com.pixelart.windforecast.ui.detailscreen

import androidx.lifecycle.ViewModel
import com.pixelart.windforecast.data.repository.ForecastRepositoryImpl

class ForecastViewModel(private val repository: ForecastRepositoryImpl): ViewModel(){

    fun getForecast(locationName: String) = repository.getForecast(locationName)

    override fun onCleared() {
        super.onCleared()
        repository.dispose()
    }
}