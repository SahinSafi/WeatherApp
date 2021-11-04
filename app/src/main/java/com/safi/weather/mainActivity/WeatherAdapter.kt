package com.safi.weather.mainActivity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.safi.weather.MapsActivity
import com.safi.weather.R
import com.safi.weather.databinding.ItemWeatherBinding
import com.safi.weather.mainActivity.dataModel.WeatherModel
import com.safi.weather.utils.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WeatherAdapter (val context: Context) :
    RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemWeatherBinding.bind(itemView)
    }

    private val cityList = mutableListOf<WeatherModel.CityList>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<WeatherModel.CityList>) {
        cityList.clear()
        cityList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val city = cityList[position]
        with(holder) {
            binding.cityName.text = city.name
            binding.cloudType.text = city.weather!![0].description
            binding.temperature.text = (city.main?.temp!! - 273.15F).toInt().toString()+
                    context.resources.getString(R.string.degree_symbol)
        }

        holder.itemView.setOnClickListener {
            Singleton.INSTANCE.cityDetails = city
            context.startActivity(Intent(context, MapsActivity::class.java))
        }

    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}