package ru.vyakhirev.bellintegratortesttask.di.repository

import dagger.Component
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import ru.vyakhirev.bellintegratortesttask.di.api.OwmApiModule
import ru.vyakhirev.bellintegratortesttask.di.database.DatabaseModule
import ru.vyakhirev.bellintegratortesttask.presentation.presenter.ListCityPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DatabaseModule::class, OwmApiModule::class, RepositoryModule::class]
)
interface RepositoryComponent {
    fun provideRepository(): Repository
    fun inject(listCityPresenter: ListCityPresenter)
}