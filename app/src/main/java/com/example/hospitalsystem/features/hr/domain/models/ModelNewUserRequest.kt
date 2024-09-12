package com.example.hospitalsystem.features.hr.domain.models

import com.google.gson.annotations.SerializedName

data class ModelNewUserRequest (
    val email: String,
    val password: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val gender: String,
    val specialist: String,
    val birthday: String,
    val status: String,
    val address: String,
    val mobile: String,
    val type: String
)