package com.example.hospitalsystem.features.manager.domain.repository

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess

interface ManagerRepositoryInterface {

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