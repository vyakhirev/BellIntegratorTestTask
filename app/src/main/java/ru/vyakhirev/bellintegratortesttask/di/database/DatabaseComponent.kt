package ru.vyakhirev.bellintegratortesttask.di.database

import dagger.Component
import ru.vyakhirev.bellintegratortesttask.data.database.CitiesDao
import ru.vyakhirev.bellintegratortesttask.data.database.CitiesDatabase
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DatabaseModule::class]
)
interface DatabaseComponent {

//    companion object {
//        fun create(databaseModule: DatabaseModule,application: Application): DatabaseComponent {
//            return DaggerDatabaseComponent.builder()
//                .databaseModule(DatabaseModule(application))
//                .build()
//        }
//    }

    fun provideDatabase(): CitiesDatabase

    fun usersDao(): CitiesDao
}