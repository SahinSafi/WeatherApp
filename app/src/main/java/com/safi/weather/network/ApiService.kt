package com.safi.hilt.network

import com.safi.weather.mainActivity.dataModel.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("2.5/find")
    suspend fun getCityList(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("cnt") cnt: String,
        @Query("appid") key: String
    ): WeatherModel
}