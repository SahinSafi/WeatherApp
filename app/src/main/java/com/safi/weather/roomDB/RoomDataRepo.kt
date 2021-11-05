package com.safi.weather.roomDB

import com.safi.assignment.roomDB.DAO
import com.safi.assignment.roomDB.tables.WeatherTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomDataRepo @Inject constructor(private val dao: DAO) {

    fun getWeather() : Flow<List<WeatherTable>> = flow {
        val response = dao.getAllWeather()
        emit(response)
    }.flowOn(Dispatchers.IO)

    suspend fun insertWeather(weatherTable: WeatherTable) = withContext(Dispatchers.IO) {
        dao.insertWeather(weatherTable)
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        dao.deleteAll()
    }

}