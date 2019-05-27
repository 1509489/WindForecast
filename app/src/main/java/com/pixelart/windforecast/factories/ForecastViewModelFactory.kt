package com.pixelart.windforecast.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pixelart.windforecast.data.repository.ForecastRepositoryImpl
import com.pixelart.windforecast.ui.detailscreen.ForecastViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ForecastViewModelFactory @Inject constructor(val repository: ForecastRepositoryImpl): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) ForecastViewModel(repository) as T
        else throw IllegalArgumentException("ViewModel not found")
    }
}