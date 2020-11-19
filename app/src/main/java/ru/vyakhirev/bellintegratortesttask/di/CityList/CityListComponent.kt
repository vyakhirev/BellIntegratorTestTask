package ru.vyakhirev.bellintegratortesttask.di.CityList

import dagger.Component
import ru.vyakhirev.bellintegratortesttask.di.AppComponent
import ru.vyakhirev.bellintegratortesttask.presentation.ListCityFragment
import ru.vyakhirev.bellintegratortesttask.presentation.MainActivity

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

    fun inject(mainActivity: MainActivity)

    fun inject(listCityFragment: ListCityFragment)
}