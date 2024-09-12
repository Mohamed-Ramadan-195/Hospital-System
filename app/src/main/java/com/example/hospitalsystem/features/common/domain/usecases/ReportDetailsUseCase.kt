package com.example.hospitalsystem.features.common.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelReportDetails
import com.example.hospitalsystem.features.common.domain.repository.CommonRepositoryInterface
import javax.inject.Inject

class ReportDetailsUseCase @Inject constructor(
    private val commonRepositoryInterface: CommonRepositoryInterface
) {

    suspend fun getReportDetails (
        reportId: Int
    ): ModelReportDetails {
        return commonRepositoryInterface.getReportDetails(reportId)
    }

}