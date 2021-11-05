package com.safi.assignment.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.safi.assignment.roomDB.tables.WeatherTable

@Database(entities = [WeatherTable::class], version = 2)
abstract class RoomDB : RoomDatabase() {

    abstract fun dao() : DAO

}