package ru.vyakhirev.bellintegratortesttask.presentation.presenter

import androidx.lifecycle.LiveData
import ru.vyakhirev.bellintegratortesttask.data.model.forecast.ForecastResponse
import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView

interface DetailCityPresenter {

    fun getForecastByCity(query: String)

    fun attachView(view: MainView)

    fun detachView()

    fun observeForecastLD(): LiveData<ForecastResponse>

    fun retainCityName(): String
}