package com.example.hospitalsystem.features.nurse.domain.models

import com.google.gson.annotations.SerializedName

data class ModelAddMeasurementRequest (
    @SerializedName("call_id")
    val caseId: Int,
    @SerializedName("blood_pressure")
    val bloodPressure: String,
    @SerializedName("sugar_analysis")
    val sugarAnalysis: String,
    @SerializedName("note")
    val note: String,
    @SerializedName("status")
    val status: String,
)