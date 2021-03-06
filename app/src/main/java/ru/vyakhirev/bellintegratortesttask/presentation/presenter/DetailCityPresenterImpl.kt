package ru.vyakhirev.bellintegratortesttask.presentation.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vyakhirev.bellintegratortesttask.data.model.forecast.ForecastResponse
import ru.vyakhirev.bellintegratortesttask.domain.GetForecastUseCase
import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView
import javax.inject.Inject

class DetailCityPresenterImpl
@Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : DetailCityPresenter {

    private var view: MainView? = null
    var disposable = CompositeDisposable()
    val forecastLiveData = MutableLiveData<ForecastResponse>()

    override fun getForecastByCity(query: String) {
        disposable.add(
            getForecastUseCase.execute(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    forecastLiveData.value = it
                }, {
                    Log.d("log2", it.message.toString())
                })
        )
    }

    override fun attachView(view: MainView) {
        this.view = view
        view.populateCity()
    }

    override fun detachView() {
        view = null
        disposable.clear()
    }

    override fun observeForecastLD(): LiveData<ForecastResponse> {
        return forecastLiveData
    }

    override fun retainCityName(): String {
        return forecastLiveData.value?.city?.cityName.toString()
    }

}