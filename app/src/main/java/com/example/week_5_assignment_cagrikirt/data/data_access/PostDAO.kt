package com.example.week_5_assignment_cagrikirt.data.data_access

import Post
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDAO  {
    @Insert
    suspend fun insertPost(post:Post)

    @Delete
    suspend fun deletePost(post:Post)

    @Query("SELECT * FROM favorite_posts")
    suspend fun getAllFavorites():List<Post>

}