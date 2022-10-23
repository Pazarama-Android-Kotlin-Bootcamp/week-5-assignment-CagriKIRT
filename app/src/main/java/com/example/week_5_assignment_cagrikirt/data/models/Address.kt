package com.example.week_5_assignment_cagrikirt.data.models


import com.example.week_5_assignment_cagrikirt.data.models.Geo
import com.google.gson.annotations.SerializedName


data class Address(
    @SerializedName("city")
    val city: String?,
    @SerializedName("geo")
    val geo: Geo?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("suite")
    val suite: String?,
    @SerializedName("zipcode")
    val zipcode: String?
)