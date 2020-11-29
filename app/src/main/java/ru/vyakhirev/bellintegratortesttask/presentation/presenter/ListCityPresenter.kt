package ru.vyakhirev.bellintegratortesttask.presentation.presenter

import androidx.lifecycle.MutableLiveData
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature
import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView

interface ListCityPresenter {

    fun getWeatherByCity(query: String)

    fun addPresetCitiesToDb()

    fun getUsersAddCityTemp()

    fun attachView(view: MainView)

    fun detachView()

    fun observeCityInfo(): MutableLiveData<List<CityTemperature>>

}
