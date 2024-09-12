package com.example.hospitalsystem.features.hr.data.repository

import com.example.hospitalsystem.features.hr.data.datasource.HrRemoteDataSourceInterface
import com.example.hospitalsystem.features.hr.domain.models.ModelEmployeeList
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserRequest
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserResponse
import com.example.hospitalsystem.features.hr.domain.repository.HrRepositoryInterface
import javax.inject.Inject

class HrRepository @Inject constructor (
    private val hrRemoteDataSourceInterface: HrRemoteDataSourceInterface
): HrRepositoryInterface {

    override suspend fun getEmployeeList (
        type: String
    ): ModelEmployeeList {
        return hrRemoteDataSourceInterface.getEmployeeList(type)
    }

    override suspend fun createNewUser (
        modelNewUserRequest: ModelNewUserRequest
    ): ModelNewUserResponse {
        return hrRemoteDataSourceInterface.createNewUser(modelNewUserRequest)
    }

}