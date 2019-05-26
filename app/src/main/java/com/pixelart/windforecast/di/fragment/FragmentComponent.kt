package com.pixelart.windforecast.di.fragment

import com.pixelart.windforecast.ui.detailscreen.DetailFragment
import com.pixelart.windforecast.ui.locationscreen.LocationFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun injectLocationScreen(locationFragment: LocationFragment)
    fun injectDetailScreen(detailFragment: DetailFragment)
}