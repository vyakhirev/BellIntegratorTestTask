package ru.vyakhirev.bellintegratortesttask.presentation.presenter

import android.annotation.SuppressLint
import android.util.Log
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

    val cityTempLiveData = MutableLiveData<MutableList<CityTemperature>>()
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
                        Log.d("kan", "${it.name}====${it.main.temp}")
                        view?.populateCity()

                        ct.add(CityTemperature(it.name, (it.main.temp!! - 273.0).toInt()))
                        cityTempLiveData.value = ct
                    },
                    {
                        view?.showError("City not found!")
                    }
                )
        )

//    Log.d("virg3",observeCityList().value.toString())
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
                .subscribe(
                    {
                    },
                    {
                        Log.d("virg4", it.toString())
                    })
        )
    }

    override fun attachView(view: MainView) {
        this.view = view
    }

    override fun detachView() {
        view = null
        disposable.clear()
    }

    override fun insertCityToDb(city: CityModel) {
        dao.insert(city).subscribeOn(Schedulers.io()).subscribe()
    }

    override fun observeCityInfo(): MutableLiveData<MutableList<CityTemperature>> {
        return cityTempLiveData
    }

    override fun observeCityList(): MutableLiveData<List<CityModel>> {
        return listCity
    }

}