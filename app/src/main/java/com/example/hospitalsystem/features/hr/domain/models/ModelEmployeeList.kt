package com.example.hospitalsystem.features.hr.domain.models

import com.google.gson.annotations.SerializedName

data class ModelEmployeeList (
    val `data`: List<EmployeeListData>,
    val message: String,
    val status: Int
)

data class EmployeeListData (
    val avatar: String,
    @SerializedName("first_name")
    val firstName: String,
    val id: Int,
    val type: String
)