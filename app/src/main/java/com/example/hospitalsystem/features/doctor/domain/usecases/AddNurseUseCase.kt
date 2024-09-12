package com.example.hospitalsystem.features.doctor.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.doctor.domain.models.ModelAddNurseRequest
import com.example.hospitalsystem.features.doctor.domain.repository.DoctorRepositoryInterface
import javax.inject.Inject

class AddNurseUseCase @Inject constructor(
    private val doctorRepositoryInterface: DoctorRepositoryInterface
) {

    suspend fun addNurse (
        modelAddNurseRequest: ModelAddNurseRequest
    ): ModelSuccess {
        return doctorRepositoryInterface.addNurse(modelAddNurseRequest)
    }

}