package com.pixelart.windforecast.data.dto


import com.google.gson.annotations.SerializedName

data class Response(
    val dt: Int,
    val wind: Wind,
    @SerializedName("dt_txt")
    val dtTxt: String
)