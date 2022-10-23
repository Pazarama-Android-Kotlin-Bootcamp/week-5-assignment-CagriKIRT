package com.example.week_5_assignment_cagrikirt.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "favorite_posts")
data class Post(

    @ColumnInfo
    @SerializedName("body")
    val body: String?,

    @ColumnInfo
    @SerializedName("userId")
    val userId: Int?,

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?
)
