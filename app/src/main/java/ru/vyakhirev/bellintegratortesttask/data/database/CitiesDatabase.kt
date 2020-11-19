package ru.vyakhirev.bellintegratortesttask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel

@Database(entities = [CityModel::class], version = 1)
abstract class CitiesDatabase : RoomDatabase() {
    abstract fun citiesDao(): CitiesDao
}