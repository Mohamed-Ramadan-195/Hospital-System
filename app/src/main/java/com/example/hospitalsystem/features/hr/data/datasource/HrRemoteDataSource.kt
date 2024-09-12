package com.example.hospitalsystem.features.hr.data.datasource

import com.example.hospitalsystem.features.hr.domain.models.ModelEmployeeList
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserRequest
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserResponse
import javax.inject.Inject

class HrRemoteDataSource @Inject constructor (
    private val hrApiCalls: HrApiCalls
): HrRemoteDataSourceInterface {

    override suspend fun getEmployeeList (
        type: String
    ): ModelEmployeeList {
        return hrApiCalls.getEmployeeList(type)
    }

    override suspend fun createNewUser (
        modelNewUserRequest: ModelNewUserRequest
    ): ModelNewUserResponse {
        return hrApiCalls.createNewUser(modelNewUserRequest)
    }
}