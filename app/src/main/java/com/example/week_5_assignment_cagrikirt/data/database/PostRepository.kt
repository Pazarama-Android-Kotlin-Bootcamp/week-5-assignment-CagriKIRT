package com.example.week_5_assignment_cagrikirt.data.database

import com.example.week_5_assignment_cagrikirt.data.models.Post
import com.example.week_5_assignment_cagrikirt.data.data_access.PostDAO

class PostRepository(private val postDAO: PostDAO) {

    suspend fun insertFavorite(post: Post) = postDAO.insertPost(post)
    suspend fun deleteFavorite(post: Post) = postDAO.deletePost(post)
    suspend fun getAllFavorite(): List<Post> = postDAO.getAllFavorites()

}