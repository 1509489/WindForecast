package com.pixelart.windforecast.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pixelart.windforecast.common.TABLE_LOCATION

@Entity(tableName = TABLE_LOCATION)
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) var id:Int? = null,
    var name: String,
    var countryCode: String
)