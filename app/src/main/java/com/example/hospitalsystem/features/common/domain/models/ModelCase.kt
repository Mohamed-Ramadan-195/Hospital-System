package com.example.hospitalsystem.features.common.domain.models

import com.google.gson.annotations.SerializedName

data class ModelCase (
    val `data`: List<CaseData>,
    val message: String,
    val status: Int
)

data class CaseData (
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    @SerializedName("patient_name")
    val patientName: String
)