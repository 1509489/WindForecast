package com.pixelart.windforecast.data.network

import com.pixelart.windforecast.data.dto.APIResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("data/2.5/forecast")
    fun getWindForecast(@Query("q") cityName: String): Single<APIResponse>
}