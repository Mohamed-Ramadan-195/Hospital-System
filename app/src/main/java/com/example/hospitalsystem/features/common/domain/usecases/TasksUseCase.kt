package com.example.hospitalsystem.features.common.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelTask
import com.example.hospitalsystem.features.common.domain.repository.CommonRepositoryInterface
import javax.inject.Inject

class TasksUseCase @Inject constructor(
    private val commonRepositoryInterface: CommonRepositoryInterface
) {

    suspend fun getTasks(date: String): ModelTask {
        return commonRepositoryInterface.getTasks(date)
    }

}