package com.example.hospitalsystem.features.common.domain.models

import com.google.gson.annotations.SerializedName

data class ModelReportDetails (
    val `data`: ReportDetailsData,
    val message: String,
    val status: Int
)

data class ReportDetailsData (
    val answer: String,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    val id: Int,
    val manger: Manger,
    val note: String,
    @SerializedName("report_name")
    val reportName: String,
    val status: String,
    val user: User
)

data class Manger (
    @SerializedName("first_name")
    val firstName: String,
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    val specialist: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class User (
    @SerializedName("first_name")
    val firstName: String,
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    val specialist: String
)