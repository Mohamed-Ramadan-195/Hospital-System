package com.example.hospitalsystem.features.hr.data.datasource

import com.example.hospitalsystem.features.hr.domain.models.ModelEmployeeList
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserRequest
import com.example.hospitalsystem.features.hr.domain.models.ModelNewUserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HrApiCalls {

    @GET("doctors")
    suspend fun getEmployeeList (
        @Query("type") type: String
    ): ModelEmployeeList

    @POST("register")
    suspend fun createNewUser (
        @Body modelNewUserRequest: ModelNewUserRequest
    ): ModelNewUserResponse

}