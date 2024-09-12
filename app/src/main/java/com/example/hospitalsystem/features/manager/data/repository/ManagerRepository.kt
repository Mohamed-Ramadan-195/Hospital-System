package com.example.hospitalsystem.features.manager.data.repository

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.manager.data.datasource.ManagerRemoteDataSourceInterface
import com.example.hospitalsystem.features.manager.domain.repository.ManagerRepositoryInterface
import javax.inject.Inject

class ManagerRepository @Inject constructor(
    private val managerRemoteDataSourceInterface: ManagerRemoteDataSourceInterface
): ManagerRepositoryInterface {

    override suspend fun createTask(
        userId: Int,
        taskName: String,
        description: String,
        toDoList: List<String>
    ): ModelSuccess {
        return managerRemoteDataSourceInterface.createTask(
            userId, taskName, description, toDoList
        )
    }

    override suspend fun createCall (
        userId: Int,
        description: String
    ): ModelSuccess {
        return managerRemoteDataSourceInterface.createCall (
            userId, description
        )
    }

}