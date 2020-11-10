package ru.vyakhirev.bellintegratortesttask.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Conditions(
    @SerializedName("dt")
    @Expose
    var dt: Long? = null,
    @SerializedName("main")
    @Expose
    var main: Main? = null,
    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null,
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null,
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null,
    @SerializedName("snow")
    @Expose
    var snow: Snow? = null,
    @SerializedName("sys")
    @Expose
    var sys: Sys? = null,
    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null
)