package com.example.hospitalsystem.features.common.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.common.domain.repository.CommonRepositoryInterface
import javax.inject.Inject

class ReportAnswerUseCase @Inject constructor(
    private val commonRepositoryInterface: CommonRepositoryInterface
) {

    suspend fun answerReport (
        id: Int,
        answer: String
    ): ModelSuccess {
        return commonRepositoryInterface.answerReport(id, answer)
    }

}