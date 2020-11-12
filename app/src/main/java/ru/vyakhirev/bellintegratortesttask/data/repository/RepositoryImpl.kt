package ru.vyakhirev.bellintegratortesttask.data.repository

import io.reactivex.Single
import ru.vyakhirev.bellintegratortesttask.data.api.OwmApi
import ru.vyakhirev.bellintegratortesttask.data.model.WeatherResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiServise: OwmApi
//    private val weatherDao:
) : Repository {
    override fun getWeatherByCity(query: String): Single<WeatherResponse> {
        return apiServise.getWeatherByCity(query)
    }

    override fun getForecastByCity(query: String): Single<WeatherResponse> {
        TODO("Not yet implemented")
    }

}