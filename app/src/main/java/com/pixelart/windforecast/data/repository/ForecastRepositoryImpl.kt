package com.pixelart.windforecast.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pixelart.windforecast.data.dto.APIResponse
import com.pixelart.windforecast.data.network.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ForecastRepositoryImpl(private val networkService: NetworkService): ForecastRepository {
    private val compositeDisposable = CompositeDisposable()
    private val forecastResponse = MutableLiveData<APIResponse>()

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

    override fun dispose() {
        compositeDisposable.clear()
    }
}