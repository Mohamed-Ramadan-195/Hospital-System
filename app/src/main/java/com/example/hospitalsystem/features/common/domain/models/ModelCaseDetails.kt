package com.example.hospitalsystem.features.common.domain.models

import com.google.gson.annotations.SerializedName

data class ModelCaseDetails (
    val `data`: CaseDetailsData,
    val message: String,
    val status: Int
)

data class CaseDetailsData (
    val age: String,
    @SerializedName("analysis_id")
    val analysisName: String,
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
    val doctorName: String,
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
    val nurseName: String,
    @SerializedName("patient_name")
    val patientName: String,
    val phone: String,
    @SerializedName("respiratory_rate")
    val respiratoryRate: String,
    val status: String,
    @SerializedName("sugar_analysis")
    val sugarAnalysis: String,
    val tempreture: String
)