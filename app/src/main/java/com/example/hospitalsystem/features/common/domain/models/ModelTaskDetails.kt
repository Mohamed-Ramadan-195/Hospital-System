package com.example.hospitalsystem.features.common.domain.models

import com.google.gson.annotations.SerializedName

data class ModelTaskDetails (
    val `data`: TaskDetails,
    val message: String,
    val status: Int
)

data class TaskDetails (
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    val id: Int,
    val note : String?,
    val status: String,
    @SerializedName("task_name")
    val taskName: String,
    @SerializedName("to_do")
    val toDo: List<ToDo>,
    val user: TaskUser
)

data class ToDo (
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    @SerializedName("task_id")
    val taskId: Int,
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class TaskUser (
    val address: String,
    val birthday: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val gender: String,
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    val mobile: String,
    val specialist: String
)