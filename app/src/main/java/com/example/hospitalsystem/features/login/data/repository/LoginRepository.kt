package com.example.hospitalsystem.features.login.data.repository

import com.example.hospitalsystem.features.login.data.datasource.LoginRemoteDataSourceInterface
import com.example.hospitalsystem.features.login.domain.models.ModelLoginRequest
import com.example.hospitalsystem.features.login.domain.models.ModelLoginResponse
import com.example.hospitalsystem.features.login.domain.repository.LoginRepositoryInterface
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginRemoteDataSourceInterface: LoginRemoteDataSourceInterface
): LoginRepositoryInterface {

    override suspend fun login (
        modelLoginRequest: ModelLoginRequest
    ): ModelLoginResponse {
        return loginRemoteDataSourceInterface.login(modelLoginRequest)
    }

}