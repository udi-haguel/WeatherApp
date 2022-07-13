package com.udihaguel.weatherapp.db.dao

import androidx.room.*
import com.udihaguel.weatherapp.model.WeatherData
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateLatestLocation(weather:WeatherData)

    // custom query
    @Query("SELECT * FROM LatestLocation WHERE id = :id")
    fun getLatestLocation(id:String): Flow<WeatherData>
}