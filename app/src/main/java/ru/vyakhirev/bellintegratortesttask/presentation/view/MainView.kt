package ru.vyakhirev.bellintegratortesttask.presentation.view

interface MainView {
    fun populateCity(name: String, temper: Int)

    fun showError(errorText: String)
}