package ru.vyakhirev.bellintegratortesttask.presentation.presenter

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vyakhirev.bellintegratortesttask.data.database.CitiesDao
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView
import javax.inject.Inject

class ListCityPresenterImpl
@Inject constructor(
    private val repository: Repository,
    private val dao: CitiesDao
) : ListCityPresenter {

    private var view: MainView? = null
    var disposable = CompositeDisposable()

    val cityTempLiveData = MutableLiveData<List<CityTemperature>>()
    var ct: MutableList<CityTemperature> = mutableListOf()
    var listCity = MutableLiveData<List<CityModel>>()

    override fun getForecastByCity(query: String) {
        TODO("Not yet implemented")
    }

    @SuppressLint("CheckResult")
    override fun getWeatherByCity(query: String) {
        disposable.add(
            repository.getWeatherByCity(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        ct.add(CityTemperature(it.name, (it.main.temp!! - 273.0).toInt()))
                        cityTempLiveData.value = ct.distinct().sortedBy { name -> name.city }
                    },
                    {
                        view?.showError("City not found!")
                        dao.deleteCity(CityModel(query))
                            .subscribeOn(Schedulers.io())
                            .subscribe()
                    }
                )

        )
        insertCityToDb(CityModel(query))
    }

    override fun loadCitiesFromDb() {
        disposable.add(
            dao.getAllcities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.map {
                        getWeatherByCity(it.name)
                    }
                }
                .subscribe()
        )
    }

    override fun attachView(view: MainView) {
        this.view = view
        view?.populateCity()
    }

    override fun detachView() {
        view = null
        disposable.clear()
    }

    override fun insertCityToDb(city: CityModel) {
        disposable.add(
            dao.insert(city)
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    override fun observeCityInfo(): MutableLiveData<List<CityTemperature>> {
        return cityTempLiveData
    }
}