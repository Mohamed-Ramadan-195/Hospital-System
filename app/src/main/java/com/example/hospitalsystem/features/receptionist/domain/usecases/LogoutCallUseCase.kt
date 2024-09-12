package com.example.hospitalsystem.features.receptionist.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.receptionist.domain.repository.ReceptionstRepositoryInterface
import javax.inject.Inject

class LogoutCallUseCase @Inject constructor(
    private val receptionstRepositoryInterface: ReceptionstRepositoryInterface
) {

    suspend fun logoutCall (id: Int): ModelSuccess {
        return receptionstRepositoryInterface.logoutCall(id)
    }

}