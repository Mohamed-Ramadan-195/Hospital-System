package com.example.hospitalsystem.features.manager.data.datasource

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import javax.inject.Inject

class ManagerRemoteDataSource @Inject constructor(
    private var managerApiCalls: ManagerApiCalls
): ManagerRemoteDataSourceInterface {

    override suspend fun createTask(
        userId: Int,
        taskName: String,
        description: String,
        toDoList: List<String>
    ): ModelSuccess {
        return managerApiCalls.createTask(
            userId, taskName, description, toDoList
        )
    }

    override suspend fun createCall (
        userId: Int,
        description: String
    ): ModelSuccess {
        return managerApiCalls.createCall (
            userId, description
        )
    }

}