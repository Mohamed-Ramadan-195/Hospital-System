package com.example.hospitalsystem.features.nurse.domain.usecase

import com.example.hospitalsystem.features.common.domain.models.ModelShowDoctorCase
import com.example.hospitalsystem.features.nurse.domain.repository.NurseRepositoryInterface
import javax.inject.Inject

class GetNurseCaseDetailsUseCase @Inject constructor(
    private val nurseRepositoryInterface: NurseRepositoryInterface
) {

    suspend fun getNurseCaseDetails (
        caseId: Int
    ): ModelShowDoctorCase {
        return nurseRepositoryInterface.showCaseDetails(caseId)
    }

}