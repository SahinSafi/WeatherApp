package com.safi.weather.utils

import com.safi.weather.mainActivity.dataModel.WeatherModel

class Singleton {

    companion object {
        val INSTANCE = Singleton()
    }
    lateinit var cityDetails: WeatherModel.CityList

}