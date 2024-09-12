package com.example.hospitalsystem.features.common.domain.usecases

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.common.domain.repository.CommonRepositoryInterface
import javax.inject.Inject

class TakeAttendanceUseCase @Inject constructor(
    private val commonRepositoryInterface: CommonRepositoryInterface
) {

    suspend fun takeAttendance(status: String): ModelSuccess {
        return commonRepositoryInterface.takeAttendance(status)
    }

}