package com.example.hospitalsystem.features.common.domain.models

import com.google.gson.annotations.SerializedName

data class ModelReport(
    val `data`: List<ReportData>,
    val message: String,
    val status: Int
)

data class ReportData (
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    @SerializedName("report_name")
    val reportName: String,
    val status: String
)