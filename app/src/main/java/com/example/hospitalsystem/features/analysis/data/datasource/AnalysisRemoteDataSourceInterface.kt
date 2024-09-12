package com.example.hospitalsystem.features.analysis.data.datasource

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody

interface AnalysisRemoteDataSourceInterface {

    suspend fun addMedicalRecord (
        caseId: RequestBody,
        image: MultipartBody.Part,
        note: RequestBody,
        status: RequestBody
    ): ResponseBody

}