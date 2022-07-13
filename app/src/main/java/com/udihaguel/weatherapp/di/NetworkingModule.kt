package com.udihaguel.weatherapp.di

import com.udihaguel.weatherapp.BuildConfig
import com.udihaguel.weatherapp.network.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    private const val BASE_URL = BuildConfig.WEATHER_BASE_URL
    private const val API_KEY = BuildConfig.WEATHER_API_KEY
    private const val HEADER = BuildConfig.WEATHER_HEADER

    @Provides
    fun provideGsonFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideAuthorizationInterceptor() = Interceptor { chain ->
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder().build()
        val newRequest = originalRequest.newBuilder()
            .addHeader(HEADER, API_KEY)
            .url(url).build()
        chain.proceed(newRequest)
    }

    @Provides
    fun provideOKHTTPClient(authInterceptor: Interceptor , loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor).build()

    @Provides
    fun provideRetrofit(httpClient : OkHttpClient , gsonConverterFactory: Converter.Factory): Retrofit =
        Retrofit.Builder()
        .client(httpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherApiService = retrofit.create(WeatherApiService::class.java)

}