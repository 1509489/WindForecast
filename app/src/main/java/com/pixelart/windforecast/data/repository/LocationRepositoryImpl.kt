package com.pixelart.windforecast.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pixelart.windforecast.data.database.LocationDatabase
import com.pixelart.windforecast.data.entities.LocationEntity
import com.pixelart.windforecast.data.network.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

class LocationRepositoryImpl(private val networkService: NetworkService, private val database: LocationDatabase):
    LocationRepository {

    private val compositeDisposable = CompositeDisposable()
    private val errorMessage = MutableLiveData<String>()


    private fun addLocations(location: LocationEntity) {
        Thread{
            database.getLocationDao().insert(location)
        }.start()
    }

    override fun getLocations(): LiveData<List<LocationEntity>> = database.getLocationDao().getLocations()

    override fun getLocationNetwork(locationName: String){
        compositeDisposable.add(
            networkService.getWindForecast(locationName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent{ response, _ ->
                    if (response.cod == "200"){
                        addLocations(LocationEntity(name = response.city.name,
                            countryCode = response.city.country,
                            population = response.city.population
                        ))
                    }
                }
                .subscribe(
                    { },
                    {error -> error.printStackTrace()
                    errorMessage.value = "City not found"}
                )
        )
    }

    override fun getErrorMessage(): LiveData<String> = errorMessage

    override fun dispose() {
        compositeDisposable.clear()
    }
}