package com.example.week_5_assignment_cagrikirt.data.local.database

import Constants
import com.example.week_5_assignment_cagrikirt.data.local.database.dao.PostDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.week_5_assignment_cagrikirt.data.local.database.converter.DaoConverter
import com.example.week_5_assignment_cagrikirt.data.local.database.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
@TypeConverters(DaoConverter::class)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        private var instance: PostsDatabase? = null

        fun getDatabase(context: Context): PostsDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, PostsDatabase::class.java, Constants.TABLE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

    }
}