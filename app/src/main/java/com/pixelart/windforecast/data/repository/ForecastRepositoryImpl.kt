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

    override fun getForecast(locationName: String): LiveData<APIResponse> {
        compositeDisposable.add(
            networkService.getWindForecast(locationName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {response -> forecastResponse.value = response},
                    {error -> error.printStackTrace()}
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
                    {response -> currentWind.value = response},
                    { error -> error.printStackTrace()}
                )
        )
        return currentWind
    }

    override fun dispose() {
        compositeDisposable.clear()
    }
}