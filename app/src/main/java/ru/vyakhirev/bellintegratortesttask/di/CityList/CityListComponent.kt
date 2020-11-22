package ru.vyakhirev.bellintegratortesttask.di.CityList

import dagger.Component
import ru.vyakhirev.bellintegratortesttask.di.AppComponent
import ru.vyakhirev.bellintegratortesttask.di.FeatureScope
import ru.vyakhirev.bellintegratortesttask.presentation.ListCityFragment

@Component(
    dependencies = [AppComponent::class],
    modules = [CityListModule::class]
)

@FeatureScope
interface CityListComponent {

    companion object {

        fun create(appComponent: AppComponent): CityListComponent {
            return DaggerCityListComponent.builder()
                .appComponent(appComponent)
                .build()
        }
    }

//    fun inject(mainActivity: MainActivity)

    fun inject(listCityFragment: ListCityFragment)
}