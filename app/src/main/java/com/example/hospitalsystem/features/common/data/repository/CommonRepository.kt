package com.example.hospitalsystem.features.common.data.repository

import com.example.hospitalsystem.features.common.data.datasource.CommonRemoteDataSourceInterface
import com.example.hospitalsystem.features.common.domain.models.ModelCase
import com.example.hospitalsystem.features.common.domain.models.ModelCaseDetails
import com.example.hospitalsystem.features.common.domain.models.ModelReport
import com.example.hospitalsystem.features.common.domain.models.ModelReportDetails
import com.example.hospitalsystem.features.common.domain.models.ModelTask
import com.example.hospitalsystem.features.common.domain.models.ModelTaskDetails
import com.example.hospitalsystem.features.common.domain.repository.CommonRepositoryInterface
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import javax.inject.Inject

class CommonRepository @Inject constructor(
    private val commonRemoteDataSourceInterface: CommonRemoteDataSourceInterface
): CommonRepositoryInterface {

    override suspend fun getCases(): ModelCase {
        return commonRemoteDataSourceInterface.getCases()
    }

    override suspend fun getCaseDetails(caseId: Int): ModelCaseDetails {
        return commonRemoteDataSourceInterface.getCaseDetails(caseId)
    }

    override suspend fun getTasks(date: String): ModelTask {
        return commonRemoteDataSourceInterface.getTasks(date)
    }

    override suspend fun getTaskDetails(id: Int): ModelTaskDetails {
        return commonRemoteDataSourceInterface.getTaskDetails(id)
    }

    override suspend fun executionTask(id: Int, note: String): ModelSuccess {
        return commonRemoteDataSourceInterface.executionTask(id, note)
    }

    override suspend fun getReports(date: String): ModelReport {
        return commonRemoteDataSourceInterface.getReports(date)
    }

    override suspend fun createReport(reportName: String, reportDescription: String): ModelSuccess {
        return commonRemoteDataSourceInterface.createReport(reportName, reportDescription)
    }

    override suspend fun getReportDetails(id: Int): ModelReportDetails {
        return commonRemoteDataSourceInterface.getReportDetails(id)
    }

    override suspend fun endReport(id: Int): ModelSuccess {
        return commonRemoteDataSourceInterface.endReport(id)
    }

    override suspend fun answerReport(id: Int, answer: String): ModelSuccess {
        return commonRemoteDataSourceInterface.answerReport(id, answer)
    }

    override suspend fun takeAttendance(status: String): ModelSuccess {
        return commonRemoteDataSourceInterface.takeAttendance(status)
    }

}