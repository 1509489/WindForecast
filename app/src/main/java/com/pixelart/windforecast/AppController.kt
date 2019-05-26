package com.pixelart.windforecast

import android.app.Application
import com.pixelart.windforecast.di.application.ApplicationComponent
import com.pixelart.windforecast.di.application.DaggerApplicationComponent
import com.pixelart.windforecast.di.network.NetworkModule

class AppController: Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .build()
    }
}