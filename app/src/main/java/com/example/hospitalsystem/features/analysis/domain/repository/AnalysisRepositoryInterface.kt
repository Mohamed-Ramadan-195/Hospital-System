package com.example.hospitalsystem.features.analysis.domain.repository

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AnalysisRepositoryInterface {

    suspend fun addMedicalRecord (
        caseId: RequestBody,
        image: MultipartBody.Part,
        note: RequestBody,
        status: RequestBody
    ): ModelSuccess

}