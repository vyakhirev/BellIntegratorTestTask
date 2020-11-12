package ru.vyakhirev.bellintegratortesttask.presentation.presenter

import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView

interface ListCityPresenter {

    fun getForecastByCity(query: String)

    fun getWeatherByCity(query: String)

    fun attachView(view: MainView)

    fun detachView()
}
