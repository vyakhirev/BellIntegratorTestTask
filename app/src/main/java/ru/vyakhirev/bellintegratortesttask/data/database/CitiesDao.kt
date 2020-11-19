package ru.vyakhirev.bellintegratortesttask.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
    fun getUserById(name: String): Single<CityModel>

}