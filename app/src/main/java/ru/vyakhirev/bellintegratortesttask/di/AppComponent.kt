package ru.vyakhirev.bellintegratortesttask.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.vyakhirev.bellintegratortesttask.App
import ru.vyakhirev.bellintegratortesttask.data.api.OwmApi
import ru.vyakhirev.bellintegratortesttask.data.database.CitiesDao
import ru.vyakhirev.bellintegratortesttask.data.database.CitiesDatabase
import ru.vyakhirev.bellintegratortesttask.di.api.OwmApiModule
import ru.vyakhirev.bellintegratortesttask.di.database.DatabaseModule
import javax.inject.Singleton

@Component(
    modules = [
        OwmApiModule::class,
        DatabaseModule::class
    ]
)
@Singleton
interface AppComponent {

    companion object {

        private var appComponent: AppComponent? = null

        fun create(application: Application): AppComponent {
            return appComponent ?: DaggerAppComponent
                .builder()
                .application(application)
                .dataBase(DatabaseModule(application))
                .build().also {
                    appComponent = it
                }
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun dataBase(databaseModule: DatabaseModule): Builder

        fun build(): AppComponent
    }


    fun provideCitiesDao(): CitiesDao

    fun provideDb(): CitiesDatabase

    fun provideOwmApi(): OwmApi

    fun inject(app: App)
}