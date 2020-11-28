package ru.vyakhirev.bellintegratortesttask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature

@Database(entities = [CityModel::class, CityTemperature::class], version = 1)
abstract class CitiesDatabase : RoomDatabase() {
    abstract fun citiesDao(): CitiesDao
}