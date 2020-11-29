package ru.vyakhirev.bellintegratortesttask.di.citylist

import dagger.Component
import ru.vyakhirev.bellintegratortesttask.di.AppComponent
import ru.vyakhirev.bellintegratortesttask.di.CityListScope
import ru.vyakhirev.bellintegratortesttask.presentation.ListCityFragment

@Component(
    dependencies = [AppComponent::class],
    modules = [CityListModule::class]
)

@CityListScope
interface CityListComponent {

    companion object {

        fun create(appComponent: AppComponent): CityListComponent {
            return DaggerCityListComponent.builder()
                .appComponent(appComponent)
                .build()
        }
    }

    fun inject(listCityFragment: ListCityFragment)
}