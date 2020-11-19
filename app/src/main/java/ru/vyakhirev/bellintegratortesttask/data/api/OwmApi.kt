package ru.vyakhirev.bellintegratortesttask.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.vyakhirev.bellintegratortesttask.data.model.WeatherResponse

interface OwmApi {
    @GET("weather")
    fun getWeatherByCity(
        @Query("q") city: String,
    ): Single<WeatherResponse>


    @GET("forecast")
    fun getForecastByCity(
        @Query("q") city: String,
        @Query("lang") lang: String = "ru"
    ): Single<WeatherResponse>
}