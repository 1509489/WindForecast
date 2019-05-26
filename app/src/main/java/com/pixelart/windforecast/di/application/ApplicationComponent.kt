package com.pixelart.windforecast.di.application

import com.pixelart.windforecast.di.network.NetworkModule
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

}