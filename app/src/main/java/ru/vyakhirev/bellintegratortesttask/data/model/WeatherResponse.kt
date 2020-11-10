package ru.vyakhirev.bellintegratortesttask.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("city") var city: City,
    @SerializedName("list") var forecast: List<ForecastDetail>
)