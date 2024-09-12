package com.example.hospitalsystem.features.analysis.data.datasource

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AnalysisApiCalls {

    @Multipart
    @POST("medical-record")
    suspend fun addMedicalRecord (
        @Part ("call_id") caseId: RequestBody,
        @Part ("image") image: MultipartBody.Part,
        @Part ("note") note: RequestBody,
        @Part ("status") status: RequestBody
    ): ResponseBody

}