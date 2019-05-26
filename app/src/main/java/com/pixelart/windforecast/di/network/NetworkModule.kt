package com.pixelart.windforecast.di.network

import com.pixelart.windforecast.common.API_KEY
import com.pixelart.windforecast.common.BASE_URL
import com.pixelart.windforecast.data.network.NetworkService
import com.pixelart.windforecast.di.application.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class NetworkModule {
    @Provides
    @ApplicationScope
    fun providesInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @ApplicationScope
    fun providesOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor {chain: Interceptor.Chain ->
                val originalRequest:Request = chain.request()
                val url:HttpUrl = originalRequest.url()
                val newUrl: HttpUrl = url.newBuilder().addQueryParameter("appid", API_KEY) as HttpUrl
                val requestBuilder: Request.Builder = originalRequest.newBuilder().url(newUrl)
                val request = requestBuilder.build()
                return@addInterceptor chain.proceed(request)
            }
            .connectTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @ApplicationScope
    @Named("weather")
    fun providesRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @ApplicationScope
    fun providesNetworkService(retrofit: Retrofit) = retrofit.create(NetworkService::class.java)
}