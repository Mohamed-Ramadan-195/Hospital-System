package com.example.hospitalsystem.di

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class NetworkState (
    val status: Status,
    val message: Any ?= null,
    val data: Any ?= null
) {

    enum class Status {
        RUNNING, FAILED, SUCCESS
    }

    companion object {

        var LOADING: NetworkState = NetworkState(Status.RUNNING)

        fun getLoaded(dataSuccess: Any?): NetworkState {
            return NetworkState(Status.SUCCESS, data = dataSuccess)
        }

        fun getErrorMessage(throwable: Throwable): NetworkState {
            return when (throwable) {
                is IOException -> {
                    NetworkState(Status.FAILED, "No Connection")
                } is SocketTimeoutException -> {
                    NetworkState(Status.FAILED, "Bad Connection")
                } is HttpException -> {
                    NetworkState(Status.FAILED, "HttpException")
                } else -> {
                    NetworkState(Status.FAILED, "Error")
                }
            }
        }

        fun getErrorMessage(message: String): NetworkState {
            return NetworkState(Status.FAILED, message)
        }
    }
}