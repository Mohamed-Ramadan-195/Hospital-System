package com.example.hospitalsystem.features.nurse.domain.repository

import com.example.hospitalsystem.features.common.domain.models.ModelShowDoctorCase
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.nurse.domain.models.ModelAddMeasurementRequest

interface NurseRepositoryInterface {

    suspend fun showCaseDetails(id: Int): ModelShowDoctorCase

    suspend fun addMeasurement(
        modelAddMeasurementRequest: ModelAddMeasurementRequest
    ): ModelSuccess

}