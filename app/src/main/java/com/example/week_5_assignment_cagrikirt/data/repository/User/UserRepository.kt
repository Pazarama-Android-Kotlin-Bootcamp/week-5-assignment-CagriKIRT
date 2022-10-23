package com.example.week_5_assignment_cagrikirt.data.repository.User

import com.example.week_5_assignment_cagrikirt.ui.model.users.Users
import retrofit2.Call

interface UserRepository {
    fun getUsers(): Call<List<Users>>
}