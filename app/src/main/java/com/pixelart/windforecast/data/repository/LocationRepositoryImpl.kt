package com.pixelart.windforecast.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pixelart.windforecast.data.database.LocationDatabase
import com.pixelart.windforecast.data.dto.City
import com.pixelart.windforecast.data.entities.LocationEntity
import com.pixelart.windforecast.data.network.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LocationRepositoryImpl(private val networkService: NetworkService, private val database: LocationDatabase):
    LocationRepository {

    private val location = MutableLiveData<City>()
    private val compositeDisposable = CompositeDisposable()
    private val errorMessage = MutableLiveData<String>()


    /*override fun addLocations(location: LocationEntity) {
        Thread{
            database.getLocationDao().insert(location)
        }.start()
    }*/

    override fun getLocations(): LiveData<List<LocationEntity>> = database.getLocationDao().getLocations()

    override fun getLocationNetwork(locationName: String){
        compositeDisposable.add(
            networkService.getWindForecast(locationName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent { response, error ->
                    database.getLocationDao().insert(
                        LocationEntity(name = response.city.name,
                            countryCode = response.city.country,
                            population = response.city.population)
                    )
                    onError(error.localizedMessage!!)
                }
                .subscribe(
                    /*{response -> location.value = response.city },
                    {error -> onError(error.message!!)
                    error.printStackTrace()}*/
                ))
    }

    override fun getErrorMessage(): LiveData<String> = errorMessage

    override fun dispose() {
        compositeDisposable.clear()
    }

    private fun onError(error: String){
        if (error.contains("city not found", true)){
            errorMessage.value = error
        } else{
            errorMessage.value = "Unable to proceed with the request please try again"
        }
    }
}