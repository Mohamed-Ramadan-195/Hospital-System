package com.example.hospitalsystem.features.analysis.domain.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody

interface AnalysisRepositoryInterface {

    suspend fun addMedicalRecord (
        caseId: RequestBody,
        image: MultipartBody.Part,
        note: RequestBody,
        status: RequestBody
    ): ResponseBody

}