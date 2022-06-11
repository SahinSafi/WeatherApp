package com.safi.weather.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safi.assignment.roomDB.tables.WeatherTable
import com.safi.weather.mainActivity.dataModel.WeatherModel
import com.safi.weather.network.core.ApiResponse
import com.safi.weather.network.ServerDataRepo
import com.safi.weather.roomDB.RoomDataRepo
import com.safi.weather.utils.CommonMethods
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val serverDataRepo: ServerDataRepo,
    private val roomDataRepo: RoomDataRepo,
    private val commonMethods: CommonMethods
) : ViewModel() {

    private val _onProgressLiveData = MutableLiveData<Boolean>()
    val onProgressLiveData : LiveData<Boolean> = _onProgressLiveData
    private val _onCityListSuccessLiveData = MutableLiveData<List<WeatherModel.CityList>>()
    val onCityListSuccessLiveData : LiveData<List<WeatherModel.CityList>> = _onCityListSuccessLiveData
    private val _onCityListFailedLiveData = MutableLiveData<String>()
    val onCityListFailedLiveData : LiveData<String> = _onCityListFailedLiveData

    fun getCityData() {
        viewModelScope.launch {
            if (commonMethods.isOnline()) {
                serverDataRepo.getCityList("23.68", "90.35", "50").collect { apiResponse ->
                    when (apiResponse) {
                        is ApiResponse.Success -> {
                            _onCityListSuccessLiveData.postValue(apiResponse.data.getList())
                            storeInRoom(apiResponse.data.getList())
                        }
                        is ApiResponse.Failure -> _onCityListFailedLiveData.postValue(apiResponse.message)
                        is ApiResponse.Progress -> _onProgressLiveData.postValue(apiResponse.progress)
                    }
                }
            } else {
                getFromRoom()
            }
        }
    }


    private fun storeInRoom(list: List<WeatherModel.CityList>) {
        viewModelScope.launch {
            roomDataRepo.deleteAll()
            for (item in list) {
                val city = WeatherTable(
                    roomID = 0,
                    id = item.id!!,
                    name = item.name!!,
                    description = item.weather!![0].description.toString(),
                    lat = item.coord?.lat!!,
                    lon = item.coord?.lon!!,
                    temp = item.main?.temp!!,
                    temp_min = item.main?.tempMin!!,
                    temp_max = item.main?.tempMax!!,
                    humidity = item.main?.humidity!!,
                    speed = item.wind?.speed!!
                )
                roomDataRepo.insertWeather(city)
            }

        }

    }


    private fun getFromRoom() {
        val cityList = mutableListOf<WeatherModel.CityList>()
        viewModelScope.launch {
            _onProgressLiveData.postValue(true)
            roomDataRepo.getWeather()
                .collect {
                    for (item in it) {
                        val city = WeatherModel.CityList()
                        val main = WeatherModel.Main()
                        val coord = WeatherModel.Coord()
                        val wind = WeatherModel.Wind()
                        val weatherList = mutableListOf<WeatherModel.Weather>()
                        val weather = WeatherModel.Weather()

                        main.temp = item.temp
                        main.tempMin = item.temp_min
                        main.tempMax = item.temp_max
                        main.humidity = item.humidity

                        coord.lat = item.lat
                        coord.lon = item.lon

                        wind.speed = item.speed
                        city.id = item.id
                        city.name = item.name

                        weather.description = item.description
                        weatherList.add(weather)

                        city.weather = weatherList
                        city.coord = coord
                        city.main = main
                        city.wind = wind
                        cityList.add(city)
                    }

                    _onProgressLiveData.postValue(false)
                    _onCityListSuccessLiveData.postValue(cityList)
                }
        }

    }

}