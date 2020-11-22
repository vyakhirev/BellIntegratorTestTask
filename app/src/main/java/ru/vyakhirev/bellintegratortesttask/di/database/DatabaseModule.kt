package ru.vyakhirev.bellintegratortesttask.di.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.vyakhirev.bellintegratortesttask.data.database.CitiesDao
import ru.vyakhirev.bellintegratortesttask.data.database.CitiesDatabase
import javax.inject.Singleton

private const val DB_NAME = "CITIES"

@Module
class DatabaseModule(mApplication: Application) {
    private val citiesDatabase: CitiesDatabase = Room
        .databaseBuilder(mApplication, CitiesDatabase::class.java, DB_NAME)
        .build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): CitiesDatabase {
        return citiesDatabase
    }

    @Singleton
    @Provides
    fun providescitiesDao(): CitiesDao {
        return this.citiesDatabase.citiesDao()
    }
}
