package com.pixelart.windforecast

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.pixelart.windforecast.data.database.LocationDatabase
import com.pixelart.windforecast.data.dto.forecast.APIResponse
import com.pixelart.windforecast.data.dto.forecast.City
import com.pixelart.windforecast.data.network.NetworkService
import com.pixelart.windforecast.data.repository.LocationRepositoryImpl
import com.pixelart.windforecast.ui.locationscreen.LocationViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class LocationViewModelTest: BaseTest() {
    
    private lateinit var viewModel: LocationViewModel
    private lateinit var repository: LocationRepositoryImpl
    private lateinit var response: APIResponse
    
    private val networkService: NetworkService = mock()
    private val database: LocationDatabase = mock()
    private val messageObservable: Observer<String> = mock()
    
    @Before
    fun `Set Up`(){
        repository = LocationRepositoryImpl(networkService, database)
        viewModel = LocationViewModel(repository)
    }
    
    @Test
    fun `Test Add Location Success`(){
        val city = mock<City> {
            on { name } doReturn "Some Location"
            on { population } doReturn 1000
            on { country } doReturn "SL"
        }
        response = APIResponse("",0.0,1, emptyList(),city)
        Assert.assertEquals("Some Location", city.name)
        
        whenever(networkService.getWindForecast(anyString())).thenReturn(Single.just(response))
        viewModel.addLocation("Some Location")
        viewModel.showMessage().observeForever(messageObservable)
        
        val captor = argumentCaptor<String>()
        verify(messageObservable).onChanged(captor.capture())
        Assert.assertEquals(captor.firstValue, "success")
        verifyNoMoreInteractions(messageObservable)
    }

    @Test
    fun `Test Add Location Failure`(){
        val throwable: Throwable = mock()
        whenever(networkService.getWindForecast(anyString())).thenReturn(Single.error(throwable))
        viewModel.addLocation("Some Location")
        viewModel.showMessage().observeForever(messageObservable)

        val captor = argumentCaptor<String>()
        verify(messageObservable).onChanged(captor.capture())
        Assert.assertEquals(captor.firstValue, "City not found")
        verifyNoMoreInteractions(messageObservable)
    }
}