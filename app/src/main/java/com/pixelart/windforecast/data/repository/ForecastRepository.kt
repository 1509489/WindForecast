package com.pixelart.windforecast.data.repository

import androidx.lifecycle.LiveData
import com.pixelart.windforecast.data.dto.APIResponse

interface ForecastRepository {
    fun getForecast(locationName: String):LiveData<APIResponse>
    fun dispose()
}