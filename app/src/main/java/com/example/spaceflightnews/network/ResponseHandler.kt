package com.example.spaceflightnews.network

import com.example.spaceflightnews.util.Resource
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * a wrapper class over the Resource class
 * better error management and reduce boiler plate code in our repository
 */
class ResponseHandler @Inject constructor() {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.Success(data)
    }

    fun <T : Any> handleException(e: Throwable): Resource<T> {
        return when (e) {
            is HttpException -> Resource.Error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.Error(
                getErrorMessage(ErrorCodes.SocketTimeOut.code),
                null
            )
            else -> Resource.Error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }


    enum class ErrorCodes(val code: Int) {
        SocketTimeOut(-1)
    }
}
