package com.example.hospitalsystem.features.common.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.common.domain.repository.CommonRepositoryInterface
import javax.inject.Inject

class CreateReportUseCase @Inject constructor(
    private val commonRepositoryInterface: CommonRepositoryInterface
) {

    suspend fun createReport(
        reportName: String,
        reportDescription: String
    ): ModelSuccess {
        return commonRepositoryInterface.createReport(reportName, reportDescription)
    }

}