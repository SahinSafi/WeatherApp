package com.safi.weather.mainActivity.dataModel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class WeatherModel {

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("cod")
    @Expose
    private var cod: String? = null

    @SerializedName("count")
    @Expose
    private var count: Int? = null

    @SerializedName("list")
    @Expose
    private var list: List<CityList> = listOf()

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getCod(): String? {
        return cod
    }

    fun setCod(cod: String?) {
        this.cod = cod
    }

    fun getCount(): Int? {
        return count
    }

    fun setCount(count: Int?) {
        this.count = count
    }

    fun getList(): List<CityList> {
        return list
    }

    fun setList(list: List<CityList>) {
        this.list = list
    }

    class CityList {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("coord")
        @Expose
        var coord: Coord? = null

        @SerializedName("main")
        @Expose
        var main: Main? = null

        @SerializedName("dt")
        @Expose
        var dt: Int? = null

        @SerializedName("wind")
        @Expose
        var wind: Wind? = null

        @SerializedName("sys")
        @Expose
        var sys: Sys? = null

        @SerializedName("rain")
        @Expose
        var rain: Any? = null

        @SerializedName("snow")
        @Expose
        var snow: Any? = null

        @SerializedName("clouds")
        @Expose
        var clouds: Clouds? = null

        @SerializedName("weather")
        @Expose
        var weather: MutableList<Weather>? = null
    }

    class Main {
        @SerializedName("temp")
        @Expose
        var temp: Double? = null

        @SerializedName("feels_like")
        @Expose
        var feelsLike: Double? = null

        @SerializedName("temp_min")
        @Expose
        var tempMin: Double? = null

        @SerializedName("temp_max")
        @Expose
        var tempMax: Double? = null

        @SerializedName("pressure")
        @Expose
        var pressure: Int? = null

        @SerializedName("humidity")
        @Expose
        var humidity: Int? = null

        @SerializedName("sea_level")
        @Expose
        var seaLevel: Int? = null

        @SerializedName("grnd_level")
        @Expose
        var grndLevel: Int? = null
    }

    class Sys {
        @SerializedName("country")
        @Expose
        var country: String? = null
    }

    class Weather {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("main")
        @Expose
        var main: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("icon")
        @Expose
        var icon: String? = null
    }

    class Wind {
        @SerializedName("speed")
        @Expose
        var speed: Double? = null

        @SerializedName("deg")
        @Expose
        var deg: Int? = null
    }

    class Coord {
        @SerializedName("lat")
        @Expose
        var lat: Double? = null

        @SerializedName("lon")
        @Expose
        var lon: Double? = null
    }

    class Clouds {
        @SerializedName("all")
        @Expose
        var all: Int? = null
    }

}