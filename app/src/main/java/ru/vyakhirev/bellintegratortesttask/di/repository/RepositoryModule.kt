package ru.vyakhirev.bellintegratortesttask.di.repository

import dagger.Binds
import dagger.Module
import ru.vyakhirev.bellintegratortesttask.data.repository.Repository
import ru.vyakhirev.bellintegratortesttask.data.repository.RepositoryImpl

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository
}