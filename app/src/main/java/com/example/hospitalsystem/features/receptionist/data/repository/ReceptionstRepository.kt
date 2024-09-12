package com.example.hospitalsystem.features.receptionist.data.repository

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.receptionist.data.datasource.ReceptionstRemoteDataSourceInterface
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCallDetails
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallRequest
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallResponse
import com.example.hospitalsystem.features.receptionist.domain.repository.ReceptionstRepositoryInterface

class ReceptionstRepository(
    private val receptionstRemoteDataSourceInterface: ReceptionstRemoteDataSourceInterface
): ReceptionstRepositoryInterface {

    override suspend fun getCalls (date: String): ModelCalls {
        return receptionstRemoteDataSourceInterface.getCalls(date)
    }

    override suspend fun getCallDetails (id: Int): ModelCallDetails {
        return receptionstRemoteDataSourceInterface.getCallDetails(id)
    }

    override suspend fun logoutCall(id: Int): ModelSuccess {
        return receptionstRemoteDataSourceInterface.logoutCall(id)
    }

    override suspend fun createCall (
        modelCreateCallRequest: ModelCreateCallRequest
    ): ModelCreateCallResponse {
        return receptionstRemoteDataSourceInterface.createCall(modelCreateCallRequest)
    }

}