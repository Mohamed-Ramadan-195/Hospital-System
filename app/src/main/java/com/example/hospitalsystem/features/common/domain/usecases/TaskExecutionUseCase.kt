package com.example.hospitalsystem.features.common.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.common.domain.repository.CommonRepositoryInterface
import javax.inject.Inject

class TaskExecutionUseCase @Inject constructor(
    private val commonRepositoryInterface: CommonRepositoryInterface
) {

    suspend fun executionTask (
        taskId: Int,
        note: String
    ): ModelSuccess {
        return commonRepositoryInterface.executionTask(taskId, note)
    }

}