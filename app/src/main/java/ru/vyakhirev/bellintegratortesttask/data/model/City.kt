package ru.vyakhirev.bellintegratortesttask.data.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("name") var cityName: String,
    @SerializedName("country") var country: String
)
//data class City(
//    @SerializedName("id")
//    @Expose
//    var id: Int? = null,
//    @SerializedName("name")
//    @Expose
//    var name: String? = null,
//    @SerializedName("coord")
//    @Expose
//    var coord: Coord? = null,
//    @SerializedName("country")
//    @Expose
//    var country: String? = null,
//    @SerializedName("population")
//    @Expose
//    var population: Int? = null)