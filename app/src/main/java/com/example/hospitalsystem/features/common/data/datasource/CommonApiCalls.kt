package com.example.hospitalsystem.features.common.data.datasource

import com.example.hospitalsystem.features.common.domain.models.ModelCase
import com.example.hospitalsystem.features.common.domain.models.ModelCaseDetails
import com.example.hospitalsystem.features.common.domain.models.ModelReport
import com.example.hospitalsystem.features.common.domain.models.ModelReportDetails
import com.example.hospitalsystem.features.common.domain.models.ModelTask
import com.example.hospitalsystem.features.common.domain.models.ModelTaskDetails
import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface CommonApiCalls {

    @GET("case")
    suspend fun getCases(): ModelCase

    @GET("case/{id}")
    suspend fun getCaseDetails (@Path("id") id: Int): ModelCaseDetails

    @GET("tasks")
    suspend fun getTasks (@Query("date") date: String): ModelTask

    @GET("tasks/{id}")
    suspend fun getTaskDetails (@Path("id") id: Int): ModelTaskDetails

    @FormUrlEncoded
    @PUT("tasks/{id}")
    suspend fun executionTask (
        @Path ("id") id: Int,
        @Field ("note") note: String
    ): ModelSuccess

    @GET("reports")
    suspend fun getReports(@Query("date") date: String): ModelReport

    @FormUrlEncoded
    @POST("reports")
    suspend fun createReport(
        @Field ("report_name") reportName: String,
        @Field ("description") reportDescription: String
    ): ModelSuccess

    @GET("reports/{id}")
    suspend fun getReportDetails (@Path("id") id:Int): ModelReportDetails

    @DELETE("reports/{id}")
    suspend fun endReport(@Path("id") id: Int): ModelSuccess

    @FormUrlEncoded
    @POST("reports/{id}")
    suspend fun answerReport(
        @Path ("id") id: Int,
        @Field ("answer") answer: String
    ): ModelSuccess

    @FormUrlEncoded
    @POST("attendance")
    suspend fun takeAttendance (
        @Field ("status") status: String
    ): ModelSuccess
}