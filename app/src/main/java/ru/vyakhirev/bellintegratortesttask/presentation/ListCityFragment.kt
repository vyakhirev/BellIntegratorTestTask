package ru.vyakhirev.bellintegratortesttask.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list_city.*
import ru.vyakhirev.bellintegratortesttask.App
import ru.vyakhirev.bellintegratortesttask.R
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature
import ru.vyakhirev.bellintegratortesttask.di.CityList.CityListComponent
import ru.vyakhirev.bellintegratortesttask.presentation.adapter.AdapterCity
import ru.vyakhirev.bellintegratortesttask.presentation.presenter.ListCityPresenter
import ru.vyakhirev.bellintegratortesttask.presentation.view.MainView
import javax.inject.Inject

class ListCityFragment : Fragment(), MainView {

    companion object {
        const val CITY_NAME = "City_Name"
    }

    @Inject
    lateinit var presenter: ListCityPresenter

    private lateinit var adapter: AdapterCity
    private var listCityTemperature = listOf<CityTemperature>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        CityListComponent.create(
            (requireActivity().applicationContext as App).getAppComponent()
        )
            .inject(this)
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        presenter.loadCitiesFromDb()
        populateCity()

        updateWeather(requireContext())

        var hasNet = isOnline(requireContext())
        Log.d("kavt", "Isonline= $hasNet")
        addBtn.setOnClickListener {
            if (cityET.text.toString().length < 3)
                showError("Error! Short city name!")
            else {
                presenter.getWeatherByCity(cityET.text.toString().toLowerCase())
                cityET.text.clear()
            }
        }


        presenter.observeCityInfo().observe(
            viewLifecycleOwner,
            {
                Log.d("kan2", it.toString())
                listCityTemperature = it
                adapter.update(it)
            }
        )
//        context?.let {
//            it.bindService(
//                Intent(it, WeatherUpdateService::class.java),
//                mConnection,
//                Context.BIND_AUTO_CREATE
//            )
//        }

//        connectivityLiveData = ConnectivityLiveData(requireContext())
//        connectivityLiveData.observe(viewLifecycleOwner,
//            { isAvailable ->
//                when (isAvailable) {
//                    true -> {
//                        Log.d("perchun1", "true")
//                        errorImg.visibility = View.GONE
//                        errorTV.visibility = View.GONE
//                        cityRV.visibility = View.VISIBLE
//                        presenter.loadCitiesFromDb()
//                    }
//                    false -> {
//                        Log.d("perchun2", "false")
//                        errorImg.visibility = View.VISIBLE
//                        errorTV.visibility = View.VISIBLE
////                        cityRV.visibility = View.GONE
//                        presenter.getWeatherFromDB()
//                    }
//                }
//            })


    }

    @SuppressLint("CheckResult")
    private fun updateWeather(context: Context) {
        Observable.timer(5000, java.util.concurrent.TimeUnit.MILLISECONDS)
            .repeat() //to perform your task every 5 seconds
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (isOnline(context))
                    presenter.loadCitiesFromDb()
            }
    }

    private fun isOnline(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }

            }
        }
        return result
    }

    private fun setupRecyclerView() {
        adapter =
            AdapterCity(
                requireContext(),
                listCityTemperature,
                cityClick = {
                    val bundle = Bundle()
                    bundle.apply {
                        putString(CITY_NAME, it.city)
                    }
                    val detailCityFragment = DetailCityFragment()
                    detailCityFragment.arguments = bundle
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, detailCityFragment)
                        .addToBackStack(null)
                        .commit()
                }
            )
        cityRV.layoutManager = LinearLayoutManager(context)
        cityRV.adapter = adapter
    }

    override fun populateCity() {
        presenter.insertCityToDb(CityModel("Moscow"))
        presenter.insertCityToDb(CityModel("Saint Petersburg"))
        presenter.insertCityToDb(CityModel("Paris"))
        setupRecyclerView()
    }

    override fun showError(errorText: String) {

        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }

}