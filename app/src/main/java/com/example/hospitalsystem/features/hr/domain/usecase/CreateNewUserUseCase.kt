package com.example.hospitalsystem.features.hr.domain.usecase

import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserRequest
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserResponse
import com.example.hospitalsystem.features.hr.domain.repository.HrRepositoryInterface
import javax.inject.Inject

class CreateNewUserUseCase @Inject constructor(
    private val hrRepositoryInterface: HrRepositoryInterface
) {

    suspend fun createNewUser (
        modelNewUserRequest: ModelNewUserRequest
    ): ModelNewUserResponse {
        return hrRepositoryInterface.createNewUser(modelNewUserRequest)
    }

}