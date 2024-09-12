package com.example.hospitalsystem.features.hr.domain.models

import com.google.gson.annotations.SerializedName

data class ModelNewUserResponse(
    val `data`: ModelNewUser,
    val message: String,
    val status: Int
)

data class ModelNewUser (
    @SerializedName("access_token")
    val accessToken: String,
    val address: String,
    val birthday: String,
    @SerializedName("created_at")
    val createdAt: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val gender: String,
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    val mobile: String,
    val specialist: String,
    val status: String,
    @SerializedName("token_type")
    val tokenType: String,
    val type: String,
    val verified: Boolean
)