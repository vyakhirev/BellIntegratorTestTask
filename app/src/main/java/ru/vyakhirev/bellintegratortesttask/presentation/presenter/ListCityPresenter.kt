package ru.vyakhirev.bellintegratortesttask.presentation.presenter

import androidx.lifecycle.MutableLiveData
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature
import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView

interface ListCityPresenter {

    fun getForecastByCity(query: String)

    fun getWeatherByCity(query: String)

    fun attachView(view: MainView)

    fun detachView()

    fun insertCityToDb(city: CityModel)

    fun loadCitiesFromDb()

    fun observeCityInfo(): MutableLiveData<MutableList<CityTemperature>>

    fun observeCityList(): MutableLiveData<List<CityModel>>
//    fun lictCityObserver():MutableLiveData<MutableList<CityTemperature>>
}
