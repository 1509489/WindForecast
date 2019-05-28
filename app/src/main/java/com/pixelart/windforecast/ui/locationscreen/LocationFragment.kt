package com.pixelart.windforecast.ui.locationscreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixelart.windforecast.MainApplication
import com.pixelart.windforecast.R
import com.pixelart.windforecast.adapter.LocationsAdapter
import com.pixelart.windforecast.di.fragment.FragmentModule
import kotlinx.android.synthetic.main.fragment_location.view.*
import kotlinx.android.synthetic.main.location_adapter_layout.view.*
import javax.inject.Inject

class LocationFragment : Fragment(), LocationsAdapter.OnItemClickedListener {
    private lateinit var rootView: View
    private lateinit var rvAdapter: LocationsAdapter

    @Inject lateinit var viewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentComponent = (activity?.application as MainApplication)
            .applicationComponent
            .newFragmentComponent(FragmentModule(this))
        fragmentComponent.injectLocationScreen(this)

        rvAdapter = LocationsAdapter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_location, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView.tvMessage.visibility = View.INVISIBLE

        rootView.rvLocations.apply {
            layoutManager = LinearLayoutManager(this@LocationFragment.context)
            addItemDecoration(DividerItemDecoration(this@LocationFragment.context, LinearLayoutManager.VERTICAL))
            adapter = rvAdapter
        }

        rootView.btnAdd.setOnClickListener {
            //Navigation.findNavController(it).navigate(R.id.addLocationFragment)
            AddLocationBottomSheet().show(fragmentManager!!, "")
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getLocations().observe(this, Observer {locations ->
            rvAdapter.submitList(locations)

            if (rvAdapter.itemCount == 0)
                rootView.tvMessage.visibility = View.VISIBLE
        })

        viewModel.showMessage().observe(this, Observer {
            if (it.contains("success", true))
                rootView.tvMessage.visibility = View.INVISIBLE
            else
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onItemClicked(position: Int) {
        val itemView = rootView.rvLocations.findViewHolderForAdapterPosition(position)?.itemView
        val direction = LocationFragmentDirections.actionLocationsToDetails(itemView?.tvLocationName?.text.toString())
        itemView?.let { Navigation.findNavController(it).navigate(direction) }
    }
}
