package com.example.week_5_assignment_cagrikirt.data.models


import com.google.gson.annotations.SerializedName

data class Geo(
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lng")
    val lng: String?
)