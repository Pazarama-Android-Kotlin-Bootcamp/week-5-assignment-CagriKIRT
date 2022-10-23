package com.example.week_5_assignment_cagrikirt.data.repository.User

import com.example.week_5_assignment_cagrikirt.data.api.ApiServiceUser
import com.example.week_5_assignment_cagrikirt.ui.model.users.Users
import retrofit2.Call

class UserRepositoryImpl constructor(
    private val apiServiceUser: ApiServiceUser
) : UserRepository {
    override fun getUsers(): Call<List<Users>> {
        return apiServiceUser.getUsers()
    }
}