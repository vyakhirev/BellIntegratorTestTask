package ru.vyakhirev.bellintegratortesttask.presentation.presenter

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature
import ru.vyakhirev.bellintegratortesttask.domain.GetUsersCityWeather
import ru.vyakhirev.bellintegratortesttask.domain.GetWeatherUseCase
import ru.vyakhirev.bellintegratortesttask.domain.PreloadCityToDb
import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView
import javax.inject.Inject

class ListCityPresenterImpl
@Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getUsersCityWeather: GetUsersCityWeather,
    private val preloadCityToDb: PreloadCityToDb
) : ListCityPresenter {

    private var view: MainView? = null
    var disposable = CompositeDisposable()

    val cityTempLiveData = MutableLiveData<List<CityTemperature>>()
    var ct: MutableList<CityTemperature> = mutableListOf()
    val presetCities =
        listOf("Moscow", "Saint Petersburg", "Atlanta", "Saratov", "Paris", "Norilsk")

    @SuppressLint("CheckResult")
    override fun getWeatherByCity(query: String) {
        disposable.add(
            getWeatherUseCase.execute(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        ct.add(it)
                        cityTempLiveData.value = ct.distinct().sortedBy { name -> name.city }
                    },
                    {
                        view?.showMessage("City not found!")
                    }
                )
        )
    }

    override fun addPresetCitiesToDb() {
        presetCities.forEach {
            preloadCityToDb.execute(CityModel(it))
        }
    }

    override fun attachView(view: MainView) {
        this.view = view
        view.populateCity()
    }

    override fun detachView() {
        Log.d("kavt", "View detached")
        view = null
        disposable.clear()
    }

    override fun observeCityInfo(): MutableLiveData<List<CityTemperature>> {
        return cityTempLiveData
    }

    override fun getUsersAddCityTemp() {
        Log.d("kavt", "GetUserAddCityTemp")
        disposable.add(
            getUsersCityWeather.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        it.map { getWeatherByCity(it.name) }
                    }, {}
                )
        )
    }

}