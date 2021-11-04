package com.safi.weather

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.safi.weather.databinding.ActivityMapsBinding
import com.safi.weather.mainActivity.dataModel.WeatherModel
import com.safi.weather.utils.Singleton

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var cityDetails: WeatherModel.CityList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cityDetails = Singleton.INSTANCE.cityDetails

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initView()
    }


    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding.back.setOnClickListener {
            onBackPressed()
        }

        binding.cityName.text = cityDetails.name
        binding.temperature.text = ((cityDetails.main?.temp!! - 273.15F).toInt().toString())+"°c"
        binding.maxTemp.text = "Max temp: "+((cityDetails.main?.tempMax!! - 273.15F).toInt().toString())+"°c"
        binding.minTemp.text = "Max temp: "+((cityDetails.main?.tempMin!! - 273.15F).toInt().toString())+"°c"
        binding.humidity.text = "Humidity: "+ cityDetails.main?.humidity.toString()
        binding.description.text = cityDetails.weather!![0].description
        binding.speed.text = "Wind speed: "+cityDetails.wind?.speed.toString()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(cityDetails.coord?.lat!!, cityDetails.coord!!.lon!!)
        mMap.addMarker(MarkerOptions().position(sydney).title(cityDetails.name))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10f))
    }
}