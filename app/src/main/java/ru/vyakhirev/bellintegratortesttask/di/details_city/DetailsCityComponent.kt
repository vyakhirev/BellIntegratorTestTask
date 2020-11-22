package ru.vyakhirev.bellintegratortesttask.di.details_city

import dagger.Component
import ru.vyakhirev.bellintegratortesttask.di.AppComponent
import ru.vyakhirev.bellintegratortesttask.di.FeatureScope
import ru.vyakhirev.bellintegratortesttask.presentation.DetailCityFragment

@Component(
    dependencies = [AppComponent::class],
    modules = [DetailsCityModule::class]
)

@FeatureScope
interface DetailsCityComponent {

    companion object {

        fun create(appComponent: AppComponent): DetailsCityComponent {
            return DaggerDetailsCityComponent.builder()
                .appComponent(appComponent)
                .build()
        }
    }

    fun inject(detailCityFragment: DetailCityFragment)
}