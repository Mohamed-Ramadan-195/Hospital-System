package com.example.hospitalsystem.features.nurse.data.datasource

import com.example.hospitalsystem.features.common.domain.models.ModelShowDoctorCase
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.nurse.domain.models.ModelAddMeasurementRequest
import javax.inject.Inject

class NurseRemoteDataSource @Inject constructor(
    private val nurseApiCalls: NurseApiCalls
): NurseRemoteDataSourceInterface {

    override suspend fun showCaseDetails(id: Int): ModelShowDoctorCase {
        return nurseApiCalls.showCaseDetails(id)
    }

    override suspend fun addMeasurement(modelAddMeasurementRequest: ModelAddMeasurementRequest): ModelSuccess {
        return nurseApiCalls.addMeasurement(modelAddMeasurementRequest)
    }

}