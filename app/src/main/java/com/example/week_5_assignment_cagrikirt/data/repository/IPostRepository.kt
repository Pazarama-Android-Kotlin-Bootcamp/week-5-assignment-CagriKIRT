package com.example.week_5_assignment_cagrikirt.data.repository

import com.example.week_5_assignment_cagrikirt.ui.model.post.Post
import com.example.week_5_assignment_cagrikirt.data.local.database.entity.PostEntity
import retrofit2.Call

interface IPostRepository {
    fun getPosts(): Call<List<Post>>
    fun getPostById(id: Int): PostEntity?
    fun insertFavoritePost(post: PostEntity)
    fun deleteFavoritePost(id: String)
    fun getFavorites(): List<PostEntity>
}