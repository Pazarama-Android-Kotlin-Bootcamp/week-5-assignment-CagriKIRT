package com.example.week_5_assignment_cagrikirt.data.data_access

import com.example.week_5_assignment_cagrikirt.data.models.Post
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDAO  {
    @Insert
     fun insertPost(post: Post)

    @Delete
     fun deletePost(post: Post)

    @Query("SELECT * FROM favorite_posts")
     fun getAllFavorites():List<Post>

}