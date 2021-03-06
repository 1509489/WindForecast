package com.pixelart.windforecast.di.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.pixelart.windforecast.common.DATABASE_NAME
import com.pixelart.windforecast.data.database.LocationDatabase
import com.pixelart.windforecast.data.network.NetworkService
import com.pixelart.windforecast.data.repository.ForecastRepositoryImpl
import com.pixelart.windforecast.data.repository.LocationRepositoryImpl
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

    @Provides
    @ApplicationScope
    fun providesLocationRepository(networkService: NetworkService, database: LocationDatabase): LocationRepositoryImpl =
            LocationRepositoryImpl(networkService, database)

    @Provides
    @ApplicationScope
    fun providesForecastRepository(networkService: NetworkService): ForecastRepositoryImpl = ForecastRepositoryImpl(networkService)
}