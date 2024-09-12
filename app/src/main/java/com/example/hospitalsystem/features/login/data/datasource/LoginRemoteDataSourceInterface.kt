package com.example.hospitalsystem.features.login.data.datasource

import com.example.hospitalsystem.features.login.domain.models.ModelLoginRequest
import com.example.hospitalsystem.features.login.domain.models.ModelLoginResponse

interface LoginRemoteDataSourceInterface {

    suspend fun login (
        modelLoginRequest: ModelLoginRequest
    ): ModelLoginResponse

}