package com.example.hospitalsystem.features.analysis.data.datasource

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

class AnalysisRemoteDataSource @Inject constructor(
    private val analysisApiCalls: AnalysisApiCalls
): AnalysisRemoteDataSourceInterface {

    override suspend fun addMedicalRecord(
        caseId: RequestBody,
        image: MultipartBody.Part,
        note: RequestBody,
        status: RequestBody
    ): ResponseBody {
        return analysisApiCalls.addMedicalRecord(
            caseId, image, note, status
        )
    }

}