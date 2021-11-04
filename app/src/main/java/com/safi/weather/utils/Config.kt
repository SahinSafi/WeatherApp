package com.safi.weather.utils

import com.safi.weather.BuildConfig

class Config {
    companion object {
        const val baseUrl = BuildConfig.BASE_URL
        const val apiKey = BuildConfig.API_KEY
    }
}