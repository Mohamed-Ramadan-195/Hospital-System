package com.example.hospitalsystem.features.doctor.domain.models

import com.google.gson.annotations.SerializedName

data class ModelAddNurseRequest (
    @SerializedName("call_id")
    val caseId: Int,
    @SerializedName("user_id")
    val nurseId: Int
)