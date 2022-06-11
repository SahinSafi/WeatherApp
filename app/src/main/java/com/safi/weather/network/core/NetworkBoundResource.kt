package com.safi.weather.network.core

import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class NetworkBoundResource<ResultType> {
    private val _result = MutableStateFlow<ApiResponse<ResultType>>(ApiResponse.loading(true))
    val result get() = _result

    init {
        downLoadData()
    }

    private fun downLoadData() {
        CoroutineScope(Dispatchers.Main).launch {
            _result.value = ApiResponse.loading(true)
            flow {
                emit(createCall())
            }.catch { response ->
                _result.value = ApiResponse.failure(message(response))
                _result.value = ApiResponse.loading(false)
            }.collect { response ->
                _result.value = ApiResponse.success(response)
                _result.value = ApiResponse.loading(false)
            }
        }
    }

    protected abstract suspend fun createCall(): ResultType

    private fun message(throwable: Throwable?): String {
        when (throwable) {
            is SocketTimeoutException -> return "Whoops! connection time out, try again!"
            is IOException -> return "No internet connection, try again!"
            is HttpException -> return try {
                val errorJsonString = throwable.response()?.errorBody()?.string()
                val errorMessage =
                    JsonParser.parseString(errorJsonString).asJsonObject["message"].asString
                errorMessage.ifEmpty { "Whoops! Something went wrong" }
            } catch (e: Exception) {
                "Unknown error occur, please try again!"
            }
        }
        return "Unknown error occur, please try again!"
    }
}