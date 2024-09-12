package com.example.hospitalsystem.features.hr.domain.usecase

import com.example.hospitalsystem.features.hr.domain.models.ModelEmployeeList
import com.example.hospitalsystem.features.hr.domain.repository.HrRepositoryInterface
import javax.inject.Inject

class GetEmployeeListUseCase @Inject constructor (
    private val hrRepositoryInterface: HrRepositoryInterface
) {

    suspend fun getEmployeeList(type: String): ModelEmployeeList {
        return hrRepositoryInterface.getEmployeeList(type)
    }

}


