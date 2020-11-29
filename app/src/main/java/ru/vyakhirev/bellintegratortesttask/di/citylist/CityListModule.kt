package ru.vyakhirev.bellintegratortesttask.di.citylist

import dagger.Binds
import dagger.Module
import dagger.Reusable
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import ru.vyakhirev.bellintegratortesttask.data.repository.RepositoryImpl
import ru.vyakhirev.bellintegratortesttask.presentation.presenter.ListCityPresenter
import ru.vyakhirev.bellintegratortesttask.presentation.presenter.ListCityPresenterImpl

@Module
abstract class CityListModule {

    @Binds
    @Reusable
    abstract fun bindsCityListPresenter(listCityPresenterImpl: ListCityPresenterImpl): ListCityPresenter

    @Binds
    @Reusable
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

}