package com.pixelart.windforecast.ui.locationscreen


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.pixelart.windforecast.AppController

import com.pixelart.windforecast.R
import com.pixelart.windforecast.di.fragment.FragmentModule
import javax.inject.Inject

class LocationFragment : Fragment() {
    private lateinit var rootView: View

    @Inject lateinit var viewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentComponent = (activity?.application as AppController)
            .applicationComponent
            .newFragmentComponent(FragmentModule(this))
        fragmentComponent.injectLocationScreen(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_location, container, false)

        viewModel.addLocation("Hatfield,UK")

        viewModel.getLocations().observe(this, Observer {
            for (location in it){
                Log.d("Locations", "${location.name} ${it.size}")
            }
        })

        viewModel.showErrorMessage().observe(this, Observer {
            Log.d("ERRM", it)
        })

        return rootView
    }


}
