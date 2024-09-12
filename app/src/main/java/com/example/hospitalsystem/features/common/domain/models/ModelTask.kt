package com.example.hospitalsystem.features.common.domain.models

import com.google.gson.annotations.SerializedName

data class ModelTask (
    val `data`: List<TaskData>,
    val message: String,
    val status: Int
)

data class TaskData (
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    val status: String,
    @SerializedName("task_name")
    val taskName: String
)