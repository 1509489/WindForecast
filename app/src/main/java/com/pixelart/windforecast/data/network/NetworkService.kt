package com.pixelart.windforecast.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("data/2.5/forecast")
    fun getWindForecast(@Query("q") cityName: String)
}