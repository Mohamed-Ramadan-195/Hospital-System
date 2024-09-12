package com.example.hospitalsystem.features.analysis.data.repository

import com.example.hospitalsystem.features.analysis.data.datasource.AnalysisRemoteDataSourceInterface
import com.example.hospitalsystem.features.analysis.domain.repository.AnalysisRepositoryInterface
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

class AnalysisRepository @Inject constructor(
    private val analysisRemoteDataSourceInterface: AnalysisRemoteDataSourceInterface
): AnalysisRepositoryInterface {

    override suspend fun addMedicalRecord(
        caseId: RequestBody,
        image: MultipartBody.Part,
        note: RequestBody,
        status: RequestBody
    ): ResponseBody {
        return analysisRemoteDataSourceInterface.addMedicalRecord(
            caseId, image, note, status
        )
    }

}