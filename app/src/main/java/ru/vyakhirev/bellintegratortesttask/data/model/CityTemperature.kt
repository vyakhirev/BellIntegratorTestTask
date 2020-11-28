package ru.vyakhirev.bellintegratortesttask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities_temperature")
data class CityTemperature(
    @PrimaryKey
    var city: String,
    var temperature: Int
)