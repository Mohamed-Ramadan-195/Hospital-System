package com.example.hospitalsystem.features.common.data.datasource

import com.example.hospitalsystem.features.common.domain.models.ModelCase
import com.example.hospitalsystem.features.common.domain.models.ModelCaseDetails
import com.example.hospitalsystem.features.common.domain.models.ModelReport
import com.example.hospitalsystem.features.common.domain.models.ModelReportDetails
import com.example.hospitalsystem.features.common.domain.models.ModelTask
import com.example.hospitalsystem.features.common.domain.models.ModelTaskDetails
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import javax.inject.Inject

class CommonRemoteDataSource @Inject constructor(
    private val commonApiCalls: CommonApiCalls
): CommonRemoteDataSourceInterface {

    override suspend fun getCases(): ModelCase {
        return commonApiCalls.getCases()
    }

    override suspend fun getCaseDetails(caseId: Int): ModelCaseDetails {
        return commonApiCalls.getCaseDetails(caseId)
    }

    override suspend fun getTasks(date: String): ModelTask {
        return commonApiCalls.getTasks(date)
    }

    override suspend fun getTaskDetails(id: Int): ModelTaskDetails {
        return commonApiCalls.getTaskDetails(id)
    }

    override suspend fun executionTask(id: Int, note: String): ModelSuccess {
        return commonApiCalls.executionTask(id, note)
    }

    override suspend fun getReports(date: String): ModelReport {
        return commonApiCalls.getReports(date)
    }

    override suspend fun createReport(
        reportName: String,
        reportDescription: String
    ): ModelSuccess {
        return commonApiCalls.createReport(reportName, reportDescription)
    }

    override suspend fun getReportDetails(id: Int): ModelReportDetails {
        return commonApiCalls.getReportDetails(id)
    }

    override suspend fun endReport(id: Int): ModelSuccess {
        return commonApiCalls.endReport(id)
    }

    override suspend fun answerReport(id: Int, answer: String): ModelSuccess {
        return commonApiCalls.answerReport(id, answer)
    }

    override suspend fun takeAttendance(status: String): ModelSuccess {
        return commonApiCalls.takeAttendance(status)
    }

}