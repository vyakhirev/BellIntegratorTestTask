package ru.vyakhirev.bellintegratortesttask.di.details_city

import dagger.Binds
import dagger.Module
import dagger.Reusable
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import ru.vyakhirev.bellintegratortesttask.data.repository.RepositoryImpl
import ru.vyakhirev.bellintegratortesttask.presentation.presenter.DetailCityPresenter
import ru.vyakhirev.bellintegratortesttask.presentation.presenter.DetailCityPresenterImpl

@Module
abstract class DetailsCityModule {

    @Binds
    @Reusable
    abstract fun bindsDetailsCityPresenter(detailsCityPresenter: DetailCityPresenterImpl): DetailCityPresenter

    @Binds
    @Reusable
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository


}