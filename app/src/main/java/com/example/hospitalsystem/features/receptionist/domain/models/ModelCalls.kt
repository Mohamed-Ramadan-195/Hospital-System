package com.example.hospitalsystem.features.receptionist.domain.models

import com.google.gson.annotations.SerializedName

data class ModelCalls (
    val `data`: List<CallsData>,
    val message: String,
    val status: Int
)

data class CallsData (
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    @SerializedName("patient_name")
    val patientName: String,
    val status: String
)