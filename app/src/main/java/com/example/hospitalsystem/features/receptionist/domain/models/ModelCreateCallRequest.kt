package com.example.hospitalsystem.features.receptionist.domain.models

import com.google.gson.annotations.SerializedName

data class ModelCreateCallRequest (
    @SerializedName("patient_name")
    val patientName: String,
    @SerializedName("doctor_id")
    val doctorId: Int,
    val age: String,
    @SerializedName("phone")
    val phoneNumber: String,
    @SerializedName("description")
    val caseDescription: String
)