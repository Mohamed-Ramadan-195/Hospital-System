package com.example.hospitalsystem.features.manager.data.datasource

import com.example.hospitalsystem.features.common.domain.models.ModelSuccess
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ManagerApiCalls {

    @FormUrlEncoded
    @POST("tasks")
    suspend fun createTask (
        @Field("user_id")  userId: Int,
        @Field("task_name") taskName: String,
        @Field("description") description: String,
        @Field("todos[]") toDoList: List<String>
    ): ModelSuccess

    @FormUrlEncoded
    @POST("calls-manger")
    suspend fun createCall (
        @Field("user_id") userId: Int,
        @Field("description") description: String
    ): ModelSuccess

}