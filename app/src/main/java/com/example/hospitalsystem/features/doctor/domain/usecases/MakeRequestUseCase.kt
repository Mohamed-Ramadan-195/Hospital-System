package com.example.hospitalsystem.features.doctor.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.doctor.domain.repository.DoctorRepositoryInterface
import javax.inject.Inject

class MakeRequestUseCase @Inject constructor(
    private val doctorRepositoryInterface: DoctorRepositoryInterface
) {

    suspend fun makeRequest (
        callId: Int,
        userId: Int,
        note: String,
        types: List<String>
    ): ModelSuccess {
        return doctorRepositoryInterface.makeRequest(
            callId, userId, note, types
        )
    }

}