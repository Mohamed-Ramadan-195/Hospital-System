package com.example.hospitalsystem.features.hr.domain.repository

import com.example.hospitalsystem.features.hr.domain.models.ModelEmployeeList
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserRequest
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserResponse

interface HrRepositoryInterface {

    suspend fun getEmployeeList (
        type: String
    ): ModelEmployeeList

    suspend fun createNewUser (
        modelNewUserRequest: ModelNewUserRequest
    ): ModelNewUserResponse
}