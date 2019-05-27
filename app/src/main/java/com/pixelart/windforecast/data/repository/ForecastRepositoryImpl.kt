package com.pixelart.windforecast.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pixelart.windforecast.data.dto.current.CurrentWindResponse
import com.pixelart.windforecast.data.dto.forecast.APIResponse
import com.pixelart.windforecast.data.network.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ForecastRepositoryImpl(private val networkService: NetworkService): ForecastRepository {
    private val compositeDisposable = CompositeDisposable()
    private val forecastResponse = MutableLiveData<APIResponse>()
    private val currentWind = MutableLiveData<CurrentWindResponse>()
    private val message = MutableLiveData<String>()

    override fun getForecast(locationName: String): LiveData<APIResponse> {
        compositeDisposable.add(
            networkService.getWindForecast(locationName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {response -> forecastResponse.value = response
                    message.value = "success"},
                    {error -> error.printStackTrace()
                    message.value = "Unable to load forecast data"}
                )
        )
        return forecastResponse
    }

    override fun getCurrentWind(locationName: String): LiveData<CurrentWindResponse> {
        compositeDisposable.add(
            networkService.getCurrentWind(locationName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {response -> currentWind.value = response
                        message.value = "success"},
                    { error -> error.printStackTrace()
                        message.value = "Unable to load forecast data" }
                )
        )
        return currentWind
    }

    override fun showMessage(): LiveData<String> = message

    override fun dispose() {
        compositeDisposable.clear()
    }
}