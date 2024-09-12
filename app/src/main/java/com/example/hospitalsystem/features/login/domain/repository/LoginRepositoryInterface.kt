package com.example.hospitalsystem.features.login.domain.repository

import com.example.hospitalsystem.features.login.domain.models.ModelLoginRequest
import com.example.hospitalsystem.features.login.domain.models.ModelLoginResponse

interface LoginRepositoryInterface {

    suspend fun login (
        modelLoginRequest: ModelLoginRequest
    ): ModelLoginResponse

}