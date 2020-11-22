package ru.vyakhirev.bellintegratortesttask.data.repository

import io.reactivex.Single
import ru.vyakhirev.bellintegratortesttask.data.model.WeatherResponse
import ru.vyakhirev.bellintegratortesttask.data.model.forecast.ForecastResponse

interface Repository {
    fun getWeatherByCity(query: String): Single<WeatherResponse>
    fun getForecastByCity(query: String): Single<ForecastResponse>
}