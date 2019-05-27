package com.pixelart.windforecast.ui.detailscreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.pixelart.windforecast.AppController
import com.pixelart.windforecast.R
import com.pixelart.windforecast.common.Utils
import com.pixelart.windforecast.data.model.Forecast
import com.pixelart.windforecast.di.fragment.FragmentModule
import com.pixelart.windforecast.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import javax.inject.Inject

class DetailFragment : Fragment() {
    private lateinit var rootView: View
    private val args: DetailFragmentArgs by navArgs()
    private val forecasts = ArrayList<Forecast>()

    @Inject lateinit var viewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentComponent = (activity?.application as AppController)
            .applicationComponent
            .newFragmentComponent(FragmentModule(this))
        fragmentComponent.injectDetailScreen(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_detail, container, false)
        return rootView
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).toolbar.title = args.locationName
    }

    override fun onResume() {
        super.onResume()
        viewModel.setMessage().observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        viewModel.setCurrentWind(args.locationName).observe(this, Observer {response ->
            rootView.apply {
                tvCurrent.text = Utils.INSTANCE.timestampToDateLong(response.dt.toLong())
                DirectionsMapper.windDirections(tvCurrentSpeed, ivCurrent,
                    response.wind.deg.toDouble(), response.wind.speed)
            }
        })

        viewModel.setWindForecast(args.locationName).observe(this, Observer { response ->
            forecasts.clear()

            for (i in 0 until response.list.size step 8){
                forecasts.add(
                    Forecast(
                        response.list[i].dt,
                        response.list[i].wind.speed,
                        response.list[i].wind.deg)
                )
            }

            rootView.apply {
                tvSecond.text = Utils.INSTANCE.timestampToDayShort(forecasts[1].timeStamp.toLong())
                tvThird.text = Utils.INSTANCE.timestampToDayShort(forecasts[2].timeStamp.toLong())
                tvForth.text = Utils.INSTANCE.timestampToDayShort(forecasts[3].timeStamp.toLong())
                tvFifth.text = Utils.INSTANCE.timestampToDayShort(forecasts[4].timeStamp.toLong())

                DirectionsMapper.windDirections(tvSecondSpeed, ivSecond, forecasts[1].direction, forecasts[1].speed)
                DirectionsMapper.windDirections(tvThirdSpeed, ivThird, forecasts[2].direction, forecasts[2].speed)
                DirectionsMapper.windDirections(tvForthSpeed, ivForth, forecasts[3].direction, forecasts[3].speed)
                DirectionsMapper.windDirections(tvFifthSpeed, ivFifth, forecasts[4].direction, forecasts[4].speed)
            }
        })
    }
}
