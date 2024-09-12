package com.example.hospitalsystem.features.analysis.domain.usecase

import com.example.hospitalsystem.features.analysis.domain.repository.AnalysisRepositoryInterface
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AddMedicalRecordUseCase @Inject constructor(
    private val analysisRepositoryInterface: AnalysisRepositoryInterface
) {

    suspend fun addMedicalRecord (
        caseId: RequestBody,
        image: MultipartBody.Part,
        note: RequestBody,
        status: RequestBody
    ): ModelSuccess {
        return analysisRepositoryInterface.addMedicalRecord(
            caseId, image, note, status
        )
    }

}