package ru.vyakhirev.bellintegratortesttask.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import ru.vyakhirev.bellintegratortesttask.data.model.CityModel

@Dao
interface CitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: CityModel): Completable

    @Query("SELECT * FROM cities")
    fun getAllcities(): Single<List<CityModel>>

    @Query("SELECT * FROM cities WHERE name=:name")
    fun getCityByName(name: String): Single<CityModel>

    @Delete
    fun deleteCity(city: CityModel): Completable
}