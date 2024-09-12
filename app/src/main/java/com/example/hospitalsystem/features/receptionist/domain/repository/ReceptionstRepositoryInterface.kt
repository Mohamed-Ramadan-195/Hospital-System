package com.example.hospitalsystem.features.receptionist.domain.repository

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCallDetails
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallRequest
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallResponse

interface ReceptionstRepositoryInterface {

    suspend fun getCalls (date: String): ModelCalls

    suspend fun getCallDetails (id: Int): ModelCallDetails

    suspend fun logoutCall (id: Int): ModelSuccess

    suspend fun createCall (
        modelCreateCallRequest: ModelCreateCallRequest
    ): ModelCreateCallResponse

}