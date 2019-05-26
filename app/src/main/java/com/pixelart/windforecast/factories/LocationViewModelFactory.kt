package com.pixelart.windforecast.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pixelart.windforecast.data.repository.LocationRepositoryImpl
import com.pixelart.windforecast.ui.locationscreen.LocationViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LocationViewModelFactory @Inject constructor(val repository: LocationRepositoryImpl): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LocationViewModel::class.java)) LocationViewModel(repository) as T
        else throw IllegalArgumentException("ViewModel not found")
    }
}