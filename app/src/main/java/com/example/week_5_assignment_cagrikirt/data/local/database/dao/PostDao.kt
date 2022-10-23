package com.example.week_5_assignment_cagrikirt.data.local.database.dao

import Constants
import androidx.room.Dao
import androidx.room.Query
import com.example.week_5_assignment_cagrikirt.data.local.database.base.BaseDao
import com.example.week_5_assignment_cagrikirt.data.local.database.entity.PostEntity

@Dao
interface PostDao  : BaseDao<PostEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME}")
    fun getAllPosts(): List<PostEntity>

    @Query("DELETE FROM ${Constants.TABLE_POST_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME} WHERE postId = :postId")
    fun getPostById(postId: String): PostEntity?

    @Query("DELETE FROM ${Constants.TABLE_POST_NAME} WHERE postId = :postId")
    fun deleteFavoriteById(postId: String)
}