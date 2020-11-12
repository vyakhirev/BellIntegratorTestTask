package ru.vyakhirev.bellintegratortesttask.presentation.presenter

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView
import javax.inject.Inject

class ListCityPresenterImpl
@Inject constructor(
    private val repository: Repository
) : ListCityPresenter {

    private var view: MainView? = null
    private val cityTemperature: CityTemperature? = null

    override fun getForecastByCity(query: String) {
        TODO("Not yet implemented")
    }

    @SuppressLint("CheckResult")
    override fun getWeatherByCity(query: String) {
        repository.getWeatherByCity(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("kan", "Kan is = $it.toString()")
                    view?.populateCity(it.name, (it.main.temp!! - 273.0).toInt())
                },
                {

                }
            )

    }

    override fun attachView(view: MainView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

}