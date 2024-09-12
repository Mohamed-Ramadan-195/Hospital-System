package com.example.hospitalsystem.features.common.domain.repository

import com.example.hospitalsystem.features.common.domain.models.ModelCase
import com.example.hospitalsystem.features.common.domain.models.ModelCaseDetails
import com.example.hospitalsystem.features.common.domain.models.ModelReport
import com.example.hospitalsystem.features.common.domain.models.ModelReportDetails
import com.example.hospitalsystem.features.common.domain.models.ModelTask
import com.example.hospitalsystem.features.common.domain.models.ModelTaskDetails
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess

interface CommonRepositoryInterface {

    suspend fun getCases(): ModelCase

    suspend fun getCaseDetails(caseId: Int): ModelCaseDetails

    suspend fun getTasks(date: String): ModelTask

    suspend fun getTaskDetails(id: Int): ModelTaskDetails

    suspend fun executionTask(id: Int, note: String): ModelSuccess

    suspend fun getReports(date: String): ModelReport

    suspend fun createReport(
        reportName: String,
        reportDescription: String
    ): ModelSuccess

    suspend fun getReportDetails(id: Int): ModelReportDetails

    suspend fun endReport(id: Int): ModelSuccess

    suspend fun answerReport(id: Int, answer: String): ModelSuccess

    suspend fun takeAttendance(status: String): ModelSuccess

}