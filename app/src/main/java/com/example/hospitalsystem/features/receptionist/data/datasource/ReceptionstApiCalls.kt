package com.example.hospitalsystem.features.receptionist.data.datasource

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCallDetails
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallRequest
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCreateCallResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ReceptionstApiCalls {

    @GET("calls")
    suspend fun getCalls (
        @Query ("date") date: String
    ): ModelCalls

    @GET("calls/{id}")
    suspend fun getCallDetails (
        @Path ("id") id: Int
    ): ModelCallDetails

    @PUT("calls/{id}")
    suspend fun logoutCall (
        @Path ("id") id:Int
    ): ModelSuccess

    @POST("calls")
    suspend fun createCall (
        @Body modelCreateCallRequest: ModelCreateCallRequest
    ): ModelCreateCallResponse

}