package com.example.hospitalsystem.features.doctor.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.doctor.domain.repository.DoctorRepositoryInterface
import javax.inject.Inject

class AcceptRejectUseCase @Inject constructor(
    private val doctorRepositoryInterface: DoctorRepositoryInterface
) {

    suspend fun acceptReject (
        callId: Int,
        status: String
    ): ModelSuccess {
        return doctorRepositoryInterface.acceptRejectCall(callId, status)
    }

}