package com.example.hospitalsystem.features.nurse.data.repository

import com.example.hospitalsystem.features.common.domain.models.ModelShowDoctorCase
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.nurse.data.datasource.NurseRemoteDataSourceInterface
import com.example.hospitalsystem.features.nurse.domain.models.ModelAddMeasurementRequest
import com.example.hospitalsystem.features.nurse.domain.repository.NurseRepositoryInterface
import javax.inject.Inject

class NurseRepository @Inject constructor (
    private val nurseRemoteDataSourceInterface: NurseRemoteDataSourceInterface
): NurseRepositoryInterface {

    override suspend fun showCaseDetails(id: Int): ModelShowDoctorCase {
        return nurseRemoteDataSourceInterface.showCaseDetails(id)
    }

    override suspend fun addMeasurement(
        modelAddMeasurementRequest: ModelAddMeasurementRequest
    ): ModelSuccess {
        return nurseRemoteDataSourceInterface.addMeasurement(modelAddMeasurementRequest)
    }

}