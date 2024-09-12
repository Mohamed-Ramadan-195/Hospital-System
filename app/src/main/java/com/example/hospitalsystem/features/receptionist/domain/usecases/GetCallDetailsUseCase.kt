package com.example.hospitalsystem.features.receptionist.domain.usecases

import com.example.hospitalsystem.features.receptionist.domain.models.ModelCallDetails
import com.example.hospitalsystem.features.receptionist.domain.repository.ReceptionstRepositoryInterface
import javax.inject.Inject

class GetCallDetailsUseCase @Inject constructor(
    private val receptionstRepositoryInterface: ReceptionstRepositoryInterface
) {

    suspend fun getCallDetails (id: Int): ModelCallDetails {
        return receptionstRepositoryInterface.getCallDetails(id)
    }

}