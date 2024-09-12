package com.example.hospitalsystem.features.hr.data.datasource

import com.example.hospitalsystem.features.hr.domain.models.ModelEmployeeList
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserRequest
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserResponse

interface HrRemoteDataSourceInterface {

    suspend fun getEmployeeList (
        type: String
    ): ModelEmployeeList

    suspend fun createNewUser (
        modelNewUserRequest: ModelNewUserRequest
    ): ModelNewUserResponse
}