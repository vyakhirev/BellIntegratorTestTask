package ru.vyakhirev.bellintegratortesttask.domain

import io.reactivex.Single
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import javax.inject.Inject

class GetUsersCityWeather @Inject
constructor(private val repository: Repository) {
    fun execute(): Single<List<CityModel>> {
        return repository.loadCitiesFromDb()
    }
}

