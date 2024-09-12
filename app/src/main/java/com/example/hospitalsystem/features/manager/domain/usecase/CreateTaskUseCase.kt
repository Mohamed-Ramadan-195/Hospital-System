package com.example.hospitalsystem.features.manager.domain.usecase

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.manager.domain.repository.ManagerRepositoryInterface
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val managerRepositoryInterface: ManagerRepositoryInterface
) {

    suspend fun createTask (
        userId: Int,
        taskName: String,
        description: String,
        toDoList: List<String>
    ): ModelSuccess {
        return managerRepositoryInterface.createTask (
            userId, taskName, description, toDoList
        )
    }

}