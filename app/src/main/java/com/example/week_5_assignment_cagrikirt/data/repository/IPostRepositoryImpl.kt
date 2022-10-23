package com.example.week_5_assignment_cagrikirt.data.repository

import com.example.week_5_assignment_cagrikirt.ui.model.post.Post
import com.example.week_5_assignment_cagrikirt.data.api.ApiService
import com.example.week_5_assignment_cagrikirt.data.local.database.PostsDatabase
import com.example.week_5_assignment_cagrikirt.data.local.database.entity.PostEntity
import retrofit2.Call

class IPostRepositoryImpl constructor(
    private val apiService: ApiService,
    private val postsDatabase: PostsDatabase
) : IPostRepository {
    override fun getPosts(): Call<List<Post>> {
        return apiService.getPosts()
    }

    override fun getPostById(id: Int): PostEntity? {
        return postsDatabase.postDao().getPostById(id.toString())
    }

    override fun insertFavoritePost(post: PostEntity) {
        return postsDatabase.postDao().insert(post)
    }

    override fun deleteFavoritePost(id: String) {
        return postsDatabase.postDao().deleteFavoriteById(id)
    }

    override fun getFavorites(): List<PostEntity> {
        return postsDatabase.postDao().getAllPosts()
    }

}