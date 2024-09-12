package com.example.hospitalsystem.features.doctor.data.repository

import com.example.hospitalsystem.features.doctor.data.datasource.DoctorRemoteDataSourceInterface
import com.example.hospitalsystem.features.doctor.domain.models.ModelAddNurseRequest
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.doctor.domain.repository.DoctorRepositoryInterface
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import javax.inject.Inject

class DoctorRepository @Inject constructor(
    private val doctorRemoteDataSourceInterface: DoctorRemoteDataSourceInterface
): DoctorRepositoryInterface {

    override suspend fun getDoctorCalls(date: String): ModelCalls {
        return doctorRemoteDataSourceInterface.getDoctorCalls(date)
    }

    override suspend fun acceptRejectCall(callId: Int, status: String): ModelSuccess {
        return doctorRemoteDataSourceInterface.acceptRejectCall(callId, status)
    }

    override suspend fun addNurse(modelAddNurseRequest: ModelAddNurseRequest): ModelSuccess {
        return doctorRemoteDataSourceInterface.addNurse(modelAddNurseRequest)
    }

    override suspend fun makeRequest(callId: Int, userId: Int, note: String, types: List<String>): ModelSuccess {
        return doctorRemoteDataSourceInterface.makeRequest(callId, userId, note, types)
    }

    override suspend fun endCase(id: Int): ModelSuccess {
        return doctorRemoteDataSourceInterface.endCase(id)
    }
}