package com.example.hospitalsystem.features.login.data.datasource.remote

import com.example.hospitalsystem.features.login.domain.models.ModelLoginRequest
import com.example.hospitalsystem.features.login.domain.models.ModelLoginResponse
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val loginApiCalls: LoginApiCalls
): LoginRemoteDataSourceInterface {

    override suspend fun login (
        modelLoginRequest: ModelLoginRequest
    ): ModelLoginResponse {
        return loginApiCalls.login(modelLoginRequest)
    }

}