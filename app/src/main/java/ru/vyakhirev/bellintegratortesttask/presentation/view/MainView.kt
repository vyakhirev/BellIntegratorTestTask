package ru.vyakhirev.bellintegratortesttask.presentation.view

interface MainView {
    fun populateCity()

    fun showError(errorText: String)
}