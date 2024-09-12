package com.example.hospitalsystem.features.common.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelTaskDetails
import com.example.hospitalsystem.features.common.domain.repository.CommonRepositoryInterface
import javax.inject.Inject

class TaskDetailsUseCase @Inject constructor(
    private val commonRepositoryInterface: CommonRepositoryInterface
) {

    suspend fun getTaskDetails (
        taskId: Int
    ): ModelTaskDetails {
        return commonRepositoryInterface.getTaskDetails(taskId)
    }

}