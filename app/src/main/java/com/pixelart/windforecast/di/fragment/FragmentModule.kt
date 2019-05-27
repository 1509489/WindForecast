package com.pixelart.windforecast.di.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pixelart.windforecast.factories.ForecastViewModelFactory
import com.pixelart.windforecast.factories.LocationViewModelFactory
import com.pixelart.windforecast.ui.detailscreen.ForecastViewModel
import com.pixelart.windforecast.ui.locationscreen.LocationViewModel
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun providesLocationViewModel(factory: LocationViewModelFactory) =
        ViewModelProviders.of(fragment, factory).get(LocationViewModel::class.java)

    @Provides
    @FragmentScope
    fun providesForecastViewModel(factory: ForecastViewModelFactory) =
        ViewModelProviders.of(fragment, factory).get(ForecastViewModel::class.java)
}