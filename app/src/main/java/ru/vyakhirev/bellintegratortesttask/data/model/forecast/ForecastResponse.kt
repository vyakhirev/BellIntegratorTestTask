package ru.vyakhirev.bellintegratortesttask.data.model.forecast

import com.google.gson.annotations.SerializedName
import ru.vyakhirev.bellintegratortesttask.data.model.*

data class ForecastResponse(
    @SerializedName("cod") val cod: Int,
    @SerializedName("message") val message: Int,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<WeatherList>,
    @SerializedName("city") val city: City
)

data class WeatherList(

    @SerializedName("dt") val dt: Int,
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("pop") val pop: Double,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("dt_txt") val dt_txt: String
)