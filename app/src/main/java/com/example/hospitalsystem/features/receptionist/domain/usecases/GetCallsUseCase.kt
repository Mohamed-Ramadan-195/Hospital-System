package com.example.hospitalsystem.features.receptionist.domain.usecases

import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import com.example.hospitalsystem.features.receptionist.domain.repository.ReceptionstRepositoryInterface
import javax.inject.Inject

class GetCallsUseCase @Inject constructor(
    private val receptionstRepositoryInterface: ReceptionstRepositoryInterface
) {

    suspend fun getCalls (date: String): ModelCalls {
        return receptionstRepositoryInterface.getCalls(date)
    }

}