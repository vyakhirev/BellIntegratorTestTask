package ru.vyakhirev.bellintegratortesttask.data.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.vyakhirev.bellintegratortesttask.data.api.OwmApi
import ru.vyakhirev.bellintegratortesttask.data.database.CitiesDao
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature
import ru.vyakhirev.bellintegratortesttask.data.model.forecast.ForecastResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiServise: OwmApi,
    private val dao: CitiesDao
) : Repository {

    override fun getWeatherByCity(query: String): Single<CityTemperature> {
        return if (apiServise.getWeatherByCity(query) != null) {
            apiServise.getWeatherByCity(query)
                .doOnSuccess {
                    insertCityToDb(CityModel(it.name))
                    insertCityTempToDb(CityTemperature(it.name, (it.main.temp!! - 273.0).toInt()))
                }
                .map {
                    CityTemperature(it.name, (it.main.temp!! - 273.0).toInt())
                }
        } else dao.getCitiesTempFromDB(query)

    }

    override fun getForecastByCity(query: String): Single<ForecastResponse> {
        return apiServise.getForecastByCity(query)
    }

    override fun insertCityToDb(city: CityModel) {
        dao.insertCityName(city)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun insertCityTempToDb(cityTemperature: CityTemperature) {
        dao.insertCityTemp(cityTemperature)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }


    override fun loadCitiesFromDb(): Single<List<CityModel>> {
        return dao.getAllcities()
    }

}