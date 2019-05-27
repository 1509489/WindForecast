package com.pixelart.windforecast.common

import java.text.SimpleDateFormat
import java.util.*

enum class Utils {
    INSTANCE;

    fun timestampToDateLong(timestamp: Long): String{
        val calender = Calendar.getInstance()
        val timeZone = calender.timeZone
        val dateFormat = SimpleDateFormat("EEE dd MMM HH:mm", Locale.ENGLISH)
        calender.timeInMillis = timestamp * 1000L
        dateFormat.timeZone = timeZone

        return dateFormat.format(calender.time)
    }

    fun timestampToDayShort(timestamp: Long): String{
        val calender = Calendar.getInstance()
        val timeZone = calender.timeZone
        val dateFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
        calender.timeInMillis = timestamp * 1000L
        dateFormat.timeZone = timeZone

        return dateFormat.format(calender.time)
    }
}