package com.example.week_5_assignment_cagrikirt.data.di

import com.example.week_5_assignment_cagrikirt.data.api.ApiService
import com.example.week_5_assignment_cagrikirt.data.local.database.PostsDatabase
import com.example.week_5_assignment_cagrikirt.data.repository.IPostRepository
import com.example.week_5_assignment_cagrikirt.data.repository.IPostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class PostsModule {

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providePostRepository(apiService: ApiService, postsDatabase: PostsDatabase): IPostRepository {
        return IPostRepositoryImpl(apiService,postsDatabase)
    }

}