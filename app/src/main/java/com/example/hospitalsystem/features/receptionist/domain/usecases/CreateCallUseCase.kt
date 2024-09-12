package com.example.hospitalsystem.features.receptionist.domain.usecases

import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallRequest
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallResponse
import com.example.hospitalsystem.features.receptionist.domain.repository.ReceptionstRepositoryInterface
import javax.inject.Inject

class CreateCallUseCase @Inject constructor(
    private val receptionstRepositoryInterface: ReceptionstRepositoryInterface
) {

    suspend fun createCall(
        modelCreateCallRequest: ModelCreateCallRequest
    ): ModelCreateCallResponse {
        return receptionstRepositoryInterface.createCall(modelCreateCallRequest)
    }

}