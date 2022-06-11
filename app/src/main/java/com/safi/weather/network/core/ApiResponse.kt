package com.safi.weather.network.core

sealed class ApiResponse<R>{
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure<T>(val message : String, val code : Int) : ApiResponse<T>()
    data class Progress<T>(val progress: Boolean) : ApiResponse<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): ApiResponse<T> = Progress(isLoading)
        fun <T> success(data: T): ApiResponse<T> = Success(data)
        fun <T> failure(message:String="", code:Int=0): ApiResponse<T> = Failure(message,code)
    }
}
