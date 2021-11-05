package com.safi.assignment.roomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.safi.assignment.roomDB.tables.WeatherTable
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {

    @Insert
    suspend fun insertWeather(weatherTable: WeatherTable): Long

    @Query("DELETE FROM city")
    suspend fun deleteAll()

    @Query("SELECT * FROM city")
    fun getAllWeather(): List<WeatherTable>

}