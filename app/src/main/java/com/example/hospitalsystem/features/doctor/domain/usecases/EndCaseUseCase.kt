package com.example.hospitalsystem.features.doctor.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.doctor.domain.repository.DoctorRepositoryInterface
import javax.inject.Inject

class EndCaseUseCase @Inject constructor(
    private val doctorRepositoryInterface: DoctorRepositoryInterface
) {

    suspend fun endCase(
        id: Int
    ): ModelSuccess {
        return doctorRepositoryInterface.endCase(id)
    }

}