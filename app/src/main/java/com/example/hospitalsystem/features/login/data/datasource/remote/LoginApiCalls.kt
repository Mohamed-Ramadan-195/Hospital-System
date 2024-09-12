package com.example.hospitalsystem.features.login.data.datasource.remote

import com.example.hospitalsystem.features.login.domain.models.ModelLoginRequest
import com.example.hospitalsystem.features.login.domain.models.ModelLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiCalls {

    @POST("login")
    suspend fun login (
        @Body modelLoginRequest: ModelLoginRequest
    ): ModelLoginResponse

}