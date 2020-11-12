package ru.vyakhirev.bellintegratortesttask.presentation.presenter

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView
import javax.inject.Inject

class ListCityPresenterImpl
@Inject constructor(
    private val repository: Repository
) : ListCityPresenter {

    private var view: MainView? = null
    var disposable = CompositeDisposable()

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
                        view?.populateCity(it.name, (it.main.temp!! - 273.0).toInt())
                    },
                    {
                        view?.showError("City not found!")
                    }
                )
        )

    }

    override fun attachView(view: MainView) {
        this.view = view
    }

    override fun detachView() {
        view = null
        disposable.clear()
    }

}