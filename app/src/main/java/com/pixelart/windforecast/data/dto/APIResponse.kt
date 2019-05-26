package com.pixelart.windforecast.data.dto


data class APIResponse(
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<Response>,
    val city: City
)