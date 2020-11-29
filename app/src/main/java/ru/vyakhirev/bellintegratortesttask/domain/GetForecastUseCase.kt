package ru.vyakhirev.bellintegratortesttask.domain

import io.reactivex.Single
import ru.vyakhirev.bellintegratortesttask.data.model.forecast.ForecastResponse
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import javax.inject.Inject

class GetForecastUseCase @Inject
constructor(private val repository: Repository) {
    fun execute(query: String): Single<ForecastResponse> {
        return repository.getForecastByCity(query)
    }
}