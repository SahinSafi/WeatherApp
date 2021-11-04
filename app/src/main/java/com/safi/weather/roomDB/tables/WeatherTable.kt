package com.safi.assignment.roomDB.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class WeatherTable(
    @PrimaryKey(autoGenerate = true)
    val roomID: Long,
    val id: Int,
    val name: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val humidity: Int,
    val speed: Double
)