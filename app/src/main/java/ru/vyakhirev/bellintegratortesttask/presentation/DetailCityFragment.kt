package ru.vyakhirev.bellintegratortesttask.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_detail_city.*
import ru.vyakhirev.bellintegratortesttask.App
import ru.vyakhirev.bellintegratortesttask.R
import ru.vyakhirev.bellintegratortesttask.di.details_city.DetailsCityComponent
import ru.vyakhirev.bellintegratortesttask.presentation.presenter.DetailCityPresenter
import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView
import javax.inject.Inject
import kotlin.math.roundToInt

class DetailCityFragment : Fragment(), MainView {

    @Inject
    lateinit var presenter: DetailCityPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DetailsCityComponent.create(
            (requireActivity().applicationContext as App).getAppComponent()
        )
            .inject(this)
        super.onViewCreated(view, savedInstanceState)
        val cityName = arguments?.getString(ListCityFragment.CITY_NAME)!!

        presenter.attachView(this)
        presenter.getForecastByCity(cityName)
//        det_city_name_TV.text =

//            cityInfo.append(arguments?.getString(ListCityFragment.CITY_NAME))

        presenter.observeForecastLD().observe(
            viewLifecycleOwner,
            {
                val cityInfo = StringBuilder()
                val str = StringBuilder()
                str.append("Дата и время:\t\t\t\t\tТемпература\t\t\tСкорость ветра\n")
                for (i in 0..10) {
                    var temp = it.list[i].main.temp!!.roundToInt() - 273
                    var wind = it.list[i].wind.speed.toString()
                    str.append("${it.list[i].dt_txt}\t\t\t\t\t\t${temp.toString()}\t\t\t\t\t\t\t\t\t${wind}\n")
                }
                det_weather_data.text = str

                cityInfo.append("Страна:${it.city.country}, город: ${it.city.cityName}")
                det_city_name_TV.text = cityInfo
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_city, container, false)
    }

    override fun populateCity() {
    }

    override fun showError(errorText: String) {
    }

}