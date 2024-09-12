package com.example.hospitalsystem.features.doctor.data.datasource

import com.example.hospitalsystem.features.doctor.domain.models.ModelAddNurseRequest
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls

interface DoctorRemoteDataSourceInterface {

    suspend fun getDoctorCalls(date: String): ModelCalls

    suspend fun acceptRejectCall(callId: Int, status: String): ModelSuccess

    suspend fun addNurse(modelAddNurseRequest: ModelAddNurseRequest): ModelSuccess

    suspend fun makeRequest(callId: Int, userId: Int, note: String, types: List<String>): ModelSuccess

    suspend fun endCase(id: Int): ModelSuccess

}