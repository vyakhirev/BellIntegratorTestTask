package ru.vyakhirev.bellintegratortesttask.di

import dagger.Component
import ru.vyakhirev.bellintegratortesttask.App
import ru.vyakhirev.bellintegratortesttask.data.api.OwmApi
import ru.vyakhirev.bellintegratortesttask.di.api.OwmApiModule
import javax.inject.Singleton

@Component(modules = [OwmApiModule::class])
@Singleton
interface AppComponent {

    companion object {

        fun create(): AppComponent {
            return DaggerAppComponent.create()
        }
    }

    fun provideOwmApi(): OwmApi

    fun inject(app: App)
}