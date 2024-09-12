package com.example.hospitalsystem.features.doctor.data.datasource

import com.example.hospitalsystem.features.doctor.domain.models.ModelAddNurseRequest
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import javax.inject.Inject

class DoctorRemoteDataSource @Inject constructor(
    private val doctorApiCalls: DoctorApiCalls
): DoctorRemoteDataSourceInterface {

    override suspend fun getDoctorCalls(date: String): ModelCalls {
        return doctorApiCalls.getDoctorCalls(date)
    }

    override suspend fun acceptRejectCall(callId: Int, status: String): ModelSuccess {
        return doctorApiCalls.acceptRejectCall(callId, status)
    }

    override suspend fun addNurse(modelAddNurseRequest: ModelAddNurseRequest): ModelSuccess {
        return doctorApiCalls.addNurse(modelAddNurseRequest)
    }

    override suspend fun makeRequest(callId: Int, userId: Int, note: String, types: List<String>): ModelSuccess {
        return doctorApiCalls.makeRequest(callId, userId, note, types)
    }

    override suspend fun endCase(id: Int): ModelSuccess {
        return doctorApiCalls.endCase(id)
    }

}