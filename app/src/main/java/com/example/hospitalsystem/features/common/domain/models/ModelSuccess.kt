package com.example.hospitalsystem.features.common.domain.models

import com.google.gson.annotations.SerializedName

data class ModelSuccess (
    @SerializedName ("message") val message: String,
    @SerializedName ("status") val status: Int
)