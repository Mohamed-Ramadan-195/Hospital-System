package com.example.hospitalsystem.features.analysis.domain.usecase

import com.example.hospitalsystem.features.analysis.domain.repository.AnalysisRepositoryInterface
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

class AddMedicalRecordUseCase @Inject constructor(
    private val analysisRepositoryInterface: AnalysisRepositoryInterface
) {

    suspend fun addMedicalRecord (
        caseId: RequestBody,
        image: MultipartBody.Part,
        note: RequestBody,
        status: RequestBody
    ): ResponseBody {
        return analysisRepositoryInterface.addMedicalRecord(
            caseId, image, note, status
        )
    }

}