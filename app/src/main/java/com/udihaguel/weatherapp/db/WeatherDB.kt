package com.udihaguel.weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.udihaguel.weatherapp.db.dao.WeatherDao
import com.udihaguel.weatherapp.model.WeatherData

const val DB_VERSION = 1
const val DB_NAME = "WeatherDb"

@Database(entities = [WeatherData::class], version = DB_VERSION)
abstract class WeatherDB: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}