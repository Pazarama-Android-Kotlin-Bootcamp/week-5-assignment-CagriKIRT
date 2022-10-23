package com.example.week_5_assignment_cagrikirt.data.api

import com.example.week_5_assignment_cagrikirt.ui.model.users.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceUser {
    @GET("users")
    fun getUsers(): Call<List<Users>>
}