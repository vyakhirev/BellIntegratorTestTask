package ru.vyakhirev.bellintegratortesttask.data.repository

import io.reactivex.Single
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature
import ru.vyakhirev.bellintegratortesttask.data.model.forecast.ForecastResponse

interface Repository {

    fun getWeatherByCity(query: String): Single<CityTemperature>

    fun getForecastByCity(query: String): Single<ForecastResponse>

    fun insertCityToDb(city: CityModel)

    fun insertCityTempToDb(cityTemperature: CityTemperature)

    fun loadCitiesFromDb(): Single<List<CityModel>>
}