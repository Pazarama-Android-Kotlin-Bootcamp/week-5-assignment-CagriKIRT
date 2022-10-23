package com.example.week_5_assignment_cagrikirt.data.api

import com.example.week_5_assignment_cagrikirt.data.models.Post
import com.example.week_5_assignment_cagrikirt.data.models.User

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by merttoptas on 8.10.2022.
 */

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("users")
    fun getUsers(): Call<List<User>>

    @DELETE("posts/{id}")
    fun deletePost(@Path("{id}") id: String): Call<Post>


}
