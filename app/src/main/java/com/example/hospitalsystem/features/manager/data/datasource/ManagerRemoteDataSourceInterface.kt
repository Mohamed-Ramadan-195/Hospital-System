package com.example.hospitalsystem.features.manager.data.datasource

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess

interface ManagerRemoteDataSourceInterface {

    suspend fun createTask (
        userId: Int,
        taskName: String,
        description: String,
        toDoList: List<String>
    ): ModelSuccess

    suspend fun createCall (
        userId: Int,
        description: String
    ): ModelSuccess

}