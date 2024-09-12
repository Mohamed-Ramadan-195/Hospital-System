package com.example.hospitalsystem.features.doctor.data.datasource

import com.example.hospitalsystem.features.doctor.domain.models.ModelAddNurseRequest
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import com.example.hospitalsystem.features.receptionist.domain.models.ModelCalls
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DoctorApiCalls {

    @GET("calls")
    suspend fun getDoctorCalls (
        @Query("date") date: String
    ): ModelCalls

    @PUT("calls-accept/{id}")
    suspend fun acceptRejectCall (
        @Path("id") callId: Int,
        @Query("status") status: String
    ): ModelSuccess

    @POST("add-nurse")
    suspend fun addNurse (
        @Body modelAddNurseRequest: ModelAddNurseRequest
    ): ModelSuccess

    @FormUrlEncoded
    @POST("make-request")
    suspend fun makeRequest (
        @Field("call_id") callId: Int,
        @Field("user_id") userId: Int,
        @Field("note") note: String,
        @Field("types[]") types: List<String>
    ) : ModelSuccess

    @PUT("calls/{id}")
    suspend fun endCase(@Path("id") id: Int): ModelSuccess

}