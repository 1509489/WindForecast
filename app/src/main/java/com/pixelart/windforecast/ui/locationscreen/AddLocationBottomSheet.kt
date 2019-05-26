package com.pixelart.windforecast.ui.locationscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pixelart.windforecast.AppController
import com.pixelart.windforecast.R
import com.pixelart.windforecast.di.fragment.FragmentModule
import kotlinx.android.synthetic.main.add_location_layout.view.*
import javax.inject.Inject

class AddLocationBottomSheet: BottomSheetDialogFragment() {
    private lateinit var rootView: View

    @Inject lateinit var viewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentComponent = (activity?.application as AppController)
            .applicationComponent
            .newFragmentComponent(FragmentModule(this))
        fragmentComponent.injectAddLocation(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.add_location_layout, container, false)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        rootView.btnAddLocation.setOnClickListener {
            viewModel.addLocation(rootView.etLocationName.text.toString())
        }
    }
}