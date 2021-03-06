package ru.vyakhirev.bellintegratortesttask.domain

import io.reactivex.Single
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import javax.inject.Inject

class GetWeatherUseCase @Inject
constructor(private val repository: Repository) {
    fun execute(query: String): Single<CityTemperature> {
        return repository.getWeatherByCity(query)
    }
}