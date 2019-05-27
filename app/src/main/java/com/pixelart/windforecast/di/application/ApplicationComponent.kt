package com.pixelart.windforecast.di.application

import com.pixelart.windforecast.di.fragment.FragmentComponent
import com.pixelart.windforecast.di.fragment.FragmentModule
import com.pixelart.windforecast.di.network.NetworkModule
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent {
    fun newFragmentComponent(fragmentModule: FragmentModule):FragmentComponent
}