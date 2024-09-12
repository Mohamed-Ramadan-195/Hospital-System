package com.example.hospitalsystem.features.doctor.domain.usecases

import com.example.hospitalsystem.features.doctor.domain.repository.DoctorRepositoryInterface
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import javax.inject.Inject

class GetDoctorCallsUseCase @Inject constructor(
    private val doctorRepositoryInterface: DoctorRepositoryInterface
) {

    suspend fun getDoctorCalls (date: String): ModelCalls {
        return doctorRepositoryInterface.getDoctorCalls(date)
    }

}