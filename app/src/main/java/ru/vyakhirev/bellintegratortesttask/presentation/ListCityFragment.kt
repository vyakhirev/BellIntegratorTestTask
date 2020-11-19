package ru.vyakhirev.bellintegratortesttask.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

    @Inject
    lateinit var presenter: ListCityPresenter

    private lateinit var adapter: AdapterCity

    private var listCityTemperature = mutableListOf<CityTemperature>()


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
//        presenter.insertCityToDb()
//        setupRecyclerView()
        addBtn.setOnClickListener {
            if (cityET.text.toString().length < 3)
                showError("Error! Short city name!")
            else {
                presenter.apply {
                    getWeatherByCity(cityET.text.toString())
                    insertCityToDb(CityModel(cityET.text.toString()))
                }
            }
        }

        presenter.loadCitiesFromDb()
        Log.d("virg7", presenter.observeCityList().value.toString())

        presenter.observeCityInfo().observe(
            viewLifecycleOwner,
            {
                Log.d("virg8", presenter.observeCityList().value.toString())
            }
        )
        presenter.observeCityInfo().observe(
            viewLifecycleOwner,
            {
                Log.d("kan2", it.toString())
                listCityTemperature = it
                adapter.update(it)
            }
        )
    }

    private fun setupRecyclerView() {
        adapter =
            AdapterCity(
                requireContext(),
                listCityTemperature,
                cityClick = {
//                    val bundle = Bundle()
//                    bundle.apply {
//                        Log.d("kan", it.avatar)
//                        putString(AVATAR_URL, it.avatar)
//                        putString(FIRST_NAME, it.first_name)
//                        putString(LAST_NAME, it.last_name)
//                        putString(EMAIL, it.email)
//                    }
//                    val userCardFragment = UserCardFragment()
//                    userCardFragment.arguments = bundle
//                    parentFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.fragmentContainer, userCardFragment)
//                        .addToBackStack(null)
//                        .commit()
                }
            )
        cityRV.layoutManager = LinearLayoutManager(context)
        cityRV.adapter = adapter
    }

    override fun populateCity() {

//        listCityTemperature.add(CityTemperature(name, temper))
        setupRecyclerView()
//        cityTempLiveData.value = listCityTemperature


    }

    override fun showError(errorText: String) {
        Toast.makeText(context, errorText, Toast.LENGTH_LONG).show()
    }

}