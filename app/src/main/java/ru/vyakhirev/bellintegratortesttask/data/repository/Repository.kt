package ru.vyakhirev.bellintegratortesttask.data.repository

import io.reactivex.Single
import ru.vyakhirev.bellintegratortesttask.data.model.WeatherResponse

interface Repository {
    fun getWeatherByCity(): Single<WeatherResponse>
    fun getForecastByCity(): Single<WeatherResponse>
}