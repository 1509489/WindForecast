package com.pixelart.windforecast.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pixelart.windforecast.common.DATABASE_VERSION
import com.pixelart.windforecast.data.dao.LocationDao
import com.pixelart.windforecast.data.entities.LocationEntity

@Database(entities = [LocationEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class LocationDatabase : RoomDatabase(){
    abstract fun getLocationDao(): LocationDao
}