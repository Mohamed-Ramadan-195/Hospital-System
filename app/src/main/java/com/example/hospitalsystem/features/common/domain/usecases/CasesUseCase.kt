package com.example.hospitalsystem.features.common.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelCase
import com.example.hospitalsystem.features.common.domain.repository.CommonRepositoryInterface
import javax.inject.Inject

class CasesUseCase @Inject constructor(
    private val commonRepositoryInterface: CommonRepositoryInterface
) {

    suspend fun getCases(): ModelCase {
        return commonRepositoryInterface.getCases()
    }

}