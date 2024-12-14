package com.example.opendotaapi.model

import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("localized_name") val localizedName: String,
    @SerializedName("img") val imgUrl: String
)
