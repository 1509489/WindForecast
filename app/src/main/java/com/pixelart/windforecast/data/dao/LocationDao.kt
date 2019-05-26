package com.pixelart.windforecast.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pixelart.windforecast.data.entities.LocationEntity

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(location: LocationEntity)

    @Query("SELECT * FROM location_table ORDER BY name ASC")
    fun getLocations():LiveData<LocationEntity>
}