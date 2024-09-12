package com.example.hospitalsystem.features.analysis.data.repository

import com.example.hospitalsystem.features.analysis.data.datasource.AnalysisRemoteDataSourceInterface
import com.example.hospitalsystem.features.analysis.domain.repository.AnalysisRepositoryInterface
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AnalysisRepository @Inject constructor(
    private val analysisRemoteDataSourceInterface: AnalysisRemoteDataSourceInterface
): AnalysisRepositoryInterface {

    override suspend fun addMedicalRecord(
        caseId: RequestBody,
        image: MultipartBody.Part,
        note: RequestBody,
        status: RequestBody
    ): ModelSuccess {
        return analysisRemoteDataSourceInterface.addMedicalRecord(
            caseId, image, note, status
        )
    }

}