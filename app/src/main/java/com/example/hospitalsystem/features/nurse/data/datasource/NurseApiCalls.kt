package com.example.hospitalsystem.features.nurse.data.datasource

import com.example.hospitalsystem.features.common.domain.models.ModelShowDoctorCase
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.nurse.domain.models.ModelAddMeasurementRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NurseApiCalls {

    @GET("case/{id}")
    suspend fun showCaseDetails(
        @Path("id") id: Int
    ): ModelShowDoctorCase

    @POST("measurement")
    suspend fun addMeasurement(
        @Body modelAddMeasurementRequest: ModelAddMeasurementRequest
    ): ModelSuccess

}