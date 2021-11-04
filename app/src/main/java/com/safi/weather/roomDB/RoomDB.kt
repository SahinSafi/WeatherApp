package com.safi.assignment.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.safi.assignment.roomDB.tables.WeatherTable

@Database(entities = [WeatherTable::class], version = 2)
abstract class RoomDB : RoomDatabase() {

    abstract fun dao() : DAO

    companion object {
        var INSTANCE: RoomDB? = null

        fun getAppDataBase(context: Context): RoomDB? {
            if (INSTANCE == null){
                synchronized(RoomDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, RoomDB::class.java, "myDatabase")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}