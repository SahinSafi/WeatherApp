package com.safi.weather.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CommonMethods @Inject constructor(@ApplicationContext private val context: Context) {

    fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (!(activeNetwork != null && activeNetwork.isConnectedOrConnecting)) {
            Toast.makeText(context, "You are in offline", Toast.LENGTH_LONG).show()
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}