package com.example.hospitalsystem.features.login.domain.usecase

import com.example.hospitalsystem.features.login.domain.models.ModelLoginRequest
import com.example.hospitalsystem.features.login.domain.models.ModelLoginResponse
import com.example.hospitalsystem.features.login.domain.repository.LoginRepositoryInterface
import javax.inject.Inject

class LoginUseCase @Inject constructor (
    private val loginRepositoryInterface: LoginRepositoryInterface
) {

    suspend fun login (
        modelLoginRequest: ModelLoginRequest
    ): ModelLoginResponse {
        return loginRepositoryInterface.login(modelLoginRequest)
    }

}