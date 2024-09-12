package com.example.hospitalsystem.features.receptionist.data.datasource

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCallDetails
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallRequest
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallResponse
import javax.inject.Inject

class ReceptionstRemoteDataSource @Inject constructor(
    private val receptionstApiCalls: ReceptionstApiCalls
): ReceptionstRemoteDataSourceInterface {

    override suspend fun getCalls(date: String): ModelCalls {
        return receptionstApiCalls.getCalls(date)
    }

    override suspend fun getCallDetails (id: Int): ModelCallDetails {
        return receptionstApiCalls.getCallDetails(id)
    }

    override suspend fun logoutCall(id: Int): ModelSuccess {
        return receptionstApiCalls.logoutCall(id)
    }

    override suspend fun createCall (
        modelCreateCallRequest: ModelCreateCallRequest
    ): ModelCreateCallResponse {
        return receptionstApiCalls.createCall(modelCreateCallRequest)
    }

}