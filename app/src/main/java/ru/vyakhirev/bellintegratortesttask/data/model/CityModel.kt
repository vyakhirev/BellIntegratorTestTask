package ru.vyakhirev.bellintegratortesttask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityModel(
    @PrimaryKey
    var name: String,
)