package com.example.hospitalsystem.features.nurse.domain.usecase

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.nurse.domain.models.ModelAddMeasurementRequest
import com.example.hospitalsystem.features.nurse.domain.repository.NurseRepositoryInterface
import javax.inject.Inject

class AddMeasurementUseCase @Inject constructor(
    private val nurseRepositoryInterface: NurseRepositoryInterface
) {

    suspend fun addMeasurement(
        modelAddMeasurementRequest: ModelAddMeasurementRequest
    ): ModelSuccess {
        return nurseRepositoryInterface.addMeasurement(modelAddMeasurementRequest)
    }

}