package com.pixelart.windforecast.data.repository

import androidx.lifecycle.LiveData
import com.pixelart.windforecast.data.dto.current.CurrentWindResponse
import com.pixelart.windforecast.data.dto.current.Wind
import com.pixelart.windforecast.data.dto.forecast.APIResponse

interface ForecastRepository {
    fun getForecast(locationName: String):LiveData<APIResponse>
    fun getCurrentWind(locationName: String):LiveData<CurrentWindResponse>
    fun dispose()
}