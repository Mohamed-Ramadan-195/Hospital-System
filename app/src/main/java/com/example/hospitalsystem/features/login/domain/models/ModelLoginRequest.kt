package com.example.hospitalsystem.features.login.domain.models

import com.google.gson.annotations.SerializedName

data class ModelLoginRequest (
    val email: String,
    val password: String,
    @SerializedName("device_token")
    val deviceToken: String
)