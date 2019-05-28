package com.pixelart.windforecast.ui.locationscreen

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pixelart.windforecast.MainApplication
import com.pixelart.windforecast.R
import com.pixelart.windforecast.di.fragment.FragmentModule
import kotlinx.android.synthetic.main.add_location_layout.view.*
import javax.inject.Inject

class AddLocationBottomSheet: BottomSheetDialogFragment() {
    private lateinit var rootView: View

    @Inject lateinit var viewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentComponent = (activity?.application as MainApplication)
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
            if (rootView.etLocationName.text.toString().isNotBlank()){
                viewModel.addLocation(rootView.etLocationName.text.toString())
            }else{
                Toast.makeText(activity, "Location Name Cannot be Blank", Toast.LENGTH_LONG).show()
            }

            viewModel.showMessage().observe(this, Observer {message ->
                if (message.contains("success", true))
                    dismiss()
            })
        }

        rootView.ibInfo.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(activity?.resources?.getString(R.string.info))
            builder.setNegativeButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }
}