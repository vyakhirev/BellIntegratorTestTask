package ru.vyakhirev.bellintegratortesttask.domain

import ru.vyakhirev.bellintegratortesttask.data.model.CityModel
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import javax.inject.Inject

class PreloadCityToDb @Inject
constructor(private val repository: Repository) {
    fun execute(city: CityModel) {
        return repository.insertCityToDb(city)
    }
}