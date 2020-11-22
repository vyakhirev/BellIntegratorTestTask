package ru.vyakhirev.bellintegratortesttask.data.model

import com.google.gson.annotations.SerializedName

//data class ForecastDetail(
//    @SerializedName("cod") val cod : Int,
//    @SerializedName("message") val message : Int,
//    @SerializedName("cnt") val cnt : Int,
//    @SerializedName("list") val list : List<List<Any?>>,
//    @SerializedName("city") val city : City
//)
//data class List<T>(
//
//    @SerializedName("dt") val dt : Int,
//    @SerializedName("main") val main : Main,
//    @SerializedName("weather") val weather : List<Weather>,
//    @SerializedName("clouds") val clouds : Clouds,
//    @SerializedName("wind") val wind : Wind,
//    @SerializedName("visibility") val visibility : Int,
//    @SerializedName("pop") val pop : Int,
//    @SerializedName("sys") val sys : Sys,
//    @SerializedName("dt_txt") val dt_txt : String
//)
//data class Weather(
//    @SerializedName("id")
//    @Expose
//    var id: Int? = null,
//    @SerializedName("main")
//    @Expose
//    var main: String? = null,
//    @SerializedName("description")
//    @Expose
//    var description: String? = null,
//    @SerializedName("icon")
//    @Expose
//    var icon: String? = null
//)
//data class Weather (
//
//    @SerializedName("id") val id : Int,
//    @SerializedName("main") val main : String,
//    @SerializedName("description") val description : String,
//    @SerializedName("icon") val icon : String
//)
data class ForecastDetail(
    @SerializedName("dt") var date: Long,
    @SerializedName("temp") var temperature: Temperature,
    @SerializedName("weather") var description: List<WeatherDescription>,
    @SerializedName("pressure") var pressure: Double,
    @SerializedName("humidity") var humidity: Double
)