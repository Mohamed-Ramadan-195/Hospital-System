package com.example.hospitalsystem.features.manager.domain.usecase

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.manager.domain.repository.ManagerRepositoryInterface
import javax.inject.Inject

class CreateCallUseCase @Inject constructor(
    private val managerRepositoryInterface: ManagerRepositoryInterface
) {

    suspend fun createCall (
        userId: Int,
        description: String
    ): ModelSuccess {
        return managerRepositoryInterface.createCall (
            userId, description
        )
    }

}