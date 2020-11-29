package ru.vyakhirev.bellintegratortesttask.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel
import ru.vyakhirev.bellintegratortesttask.data.model.CityTemperature

@Dao
interface CitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityName(city: CityModel): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityTemp(city: CityTemperature): Completable

    @Query("SELECT * FROM cities_temperature")
    fun getAllCitiesTemp(): Single<List<CityTemperature>>

    @Query("SELECT * FROM cities_temperature WHERE city=:name")
    fun getCitiesTempFromDB(name: String): Single<CityTemperature>

    @Query("SELECT * FROM cities")
    fun getAllcities(): Single<List<CityModel>>

    @Query("SELECT * FROM cities WHERE name=:name")
    fun getCityByName(name: String): Single<CityModel>

    @Delete
    fun deleteCity(city: CityModel): Completable
}