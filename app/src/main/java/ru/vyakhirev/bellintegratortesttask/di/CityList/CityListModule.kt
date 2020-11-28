package ru.vyakhirev.bellintegratortesttask.di.CityList

import dagger.Binds
import dagger.Module
import dagger.Reusable
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import ru.vyakhirev.bellintegratortesttask.data.repository.RepositoryImpl
import ru.vyakhirev.bellintegratortesttask.presentation.presenter.ListCityPresenter
import ru.vyakhirev.bellintegratortesttask.presentation.presenter.ListCityPresenterImpl

@Module
abstract class CityListModule {

//    companion object{
//        @JvmStatic
//        @Provides
//        fun provideConnectivityManager(context: Context): ConnectivityManager =
//              context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//
//        @JvmStatic
//        @Provides
//        fun provideConectivityLiveData(context: Context, connectivityManager: ConnectivityManager): ConnectivityLiveData =
//            ConnectivityLiveData(connectivityManager)
//    }

    @Binds
    @Reusable
    abstract fun bindsCityListPresenter(listCityPresenterImpl: ListCityPresenterImpl): ListCityPresenter

    @Binds
    @Reusable
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository


}