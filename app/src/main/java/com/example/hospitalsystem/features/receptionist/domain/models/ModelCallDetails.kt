package com.example.hospitalsystem.features.receptionist.domain.models

import com.google.gson.annotations.SerializedName

data class ModelCallDetails (
    val `data`: CallDetailsData,
    val message: String,
    val status: Int
)

data class CallDetailsData (
    val age: String,
    @SerializedName("analysis_id")
    val analysisId: String,
    @SerializedName("blood_pressure")
    val bloodPressure: String,
    @SerializedName("case_status")
    val caseStatus: String,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    @SerializedName("doc_id")
    val docId: Int,
    @SerializedName("doctor_id")
    val doctorId: String,
    @SerializedName("fluid_balance")
    val fluidBalance: String,
    @SerializedName("heart_rate")
    val heartRate: String,
    val id: Int,
    val image: String,
    @SerializedName("measurement_note")
    val measurementNote: String,
    @SerializedName("medical_record_note")
    val medicalRecordNote: String,
    @SerializedName("nurse_id")
    val nurseId: String,
    @SerializedName("patient_name")
    val patientName: String,
    val phone: String,
    @SerializedName("respiratory_rate")
    val respiratoryRate: String,
    val status: String,
    @SerializedName("sugar_analysis")
    val sugarAnalysis: String,
    @SerializedName("tempreture")
    val temperature: String
)