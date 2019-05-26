package com.pixelart.windforecast.di.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.pixelart.windforecast.common.DATABASE_NAME
import com.pixelart.windforecast.data.database.LocationDatabase
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationScope
    fun providesContext():Context = application

    @Provides
    @ApplicationScope
    fun providesDatabase(): LocationDatabase = Room.databaseBuilder(application.applicationContext,
        LocationDatabase::class.java, DATABASE_NAME).build()
}