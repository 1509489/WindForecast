package com.pixelart.windforecast

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.pixelart.windforecast.data.dto.current.CurrentWindResponse
import com.pixelart.windforecast.data.dto.current.Wind
import com.pixelart.windforecast.data.dto.forecast.APIResponse
import com.pixelart.windforecast.data.dto.forecast.City
import com.pixelart.windforecast.data.network.NetworkService
import com.pixelart.windforecast.data.repository.ForecastRepositoryImpl
import com.pixelart.windforecast.ui.detailscreen.ForecastViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class ForecastViewModelTest: BaseTest() {
    private lateinit var viewModel: ForecastViewModel
    private lateinit var forecastResponse: APIResponse
    private lateinit var currentResponse: CurrentWindResponse
    private lateinit var repository: ForecastRepositoryImpl

    private val networkService: NetworkService = mock()
    private val forecastObservable: Observer<APIResponse> = mock()
    private val currentObservable: Observer<CurrentWindResponse> = mock()
    private val messageObservable: Observer<String> = mock()

    @Before
    fun `Set Up`(){
        repository = ForecastRepositoryImpl(networkService)
        viewModel = ForecastViewModel(repository)

        val city: City = mock{
            on { name } doReturn "Some Location"
            on { country } doReturn "SL"
        }
        forecastResponse = APIResponse("",0.0,1, emptyList(), city)

        val wind: Wind = mock{
            on { speed } doReturn 8.5
            on { deg } doReturn 90
        }
        currentResponse = CurrentWindResponse(wind, 1)
    }

    @Test
    fun `Test Get Wind Forecast Success`(){
        whenever(networkService.getWindForecast(anyString())).thenReturn(Single.just(forecastResponse))
        viewModel.setWindForecast("Some Location").observeForever(forecastObservable)

        val forecastCaptor = argumentCaptor<APIResponse>()
        verify(forecastObservable).onChanged(forecastCaptor.capture())
        Assert.assertEquals(forecastCaptor.firstValue.city.name, "Some Location")
        verifyNoMoreInteractions(forecastObservable)
        viewModel.setMessage().observeForever(messageObservable)

        val captor = argumentCaptor<String>()
        verify(messageObservable).onChanged(captor.capture())
        Assert.assertEquals(captor.firstValue, "success")
        verifyNoMoreInteractions(messageObservable)
    }

    @Test
    fun `Test Get Wind Forecast Failure`(){
        val throwable: Throwable = mock()
        whenever(networkService.getWindForecast(anyString())).thenReturn(Single.error(throwable))
        viewModel.setWindForecast("Some Location").observeForever(forecastObservable)
        viewModel.setMessage().observeForever(messageObservable)

        val captor = argumentCaptor<String>()
        verify(messageObservable).onChanged(captor.capture())
        Assert.assertEquals(captor.firstValue, "Unable to load forecast data")
        verifyNoMoreInteractions(messageObservable)
    }

    @Test
    fun `Test Get Current Wind Success`(){
        whenever(networkService.getCurrentWind(anyString())).thenReturn(Single.just(currentResponse))
        viewModel.setCurrentWind("Some Location").observeForever(currentObservable)
        viewModel.setMessage().observeForever(messageObservable)

        val windCaptor = argumentCaptor<CurrentWindResponse>()
        verify(currentObservable).onChanged(windCaptor.capture())
        Assert.assertEquals(windCaptor.firstValue.wind.speed, 8.5, 0.0)
        verifyNoMoreInteractions(forecastObservable)

        val captor = argumentCaptor<String>()
        verify(messageObservable).onChanged(captor.capture())
        Assert.assertEquals(captor.firstValue, "success")
        verifyNoMoreInteractions(messageObservable)
    }

    @Test
    fun `Test Get Current Wind Failure`(){
        val throwable: Throwable = mock()
        whenever(networkService.getCurrentWind(anyString())).thenReturn(Single.error(throwable))
        viewModel.setCurrentWind("Some Location").observeForever(currentObservable)
        viewModel.setMessage().observeForever(messageObservable)

        val captor = argumentCaptor<String>()
        verify(messageObservable).onChanged(captor.capture())
        Assert.assertEquals(captor.firstValue, "Unable to load forecast data")
        verifyNoMoreInteractions(messageObservable)
    }
}