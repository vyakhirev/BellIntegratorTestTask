package ru.vyakhirev.bellintegratortesttask.presentation.view

interface MainView {
    //    fun showSpinner()
//    fun hideSpinner()
//    fun updateForecast(forecast: List<ForecastItemViewModel>)
//    fun showErrorToast(error: ErrorTypes)
    fun populateCity(name: String, temper: Int)

    fun showError(errorText: String)
}