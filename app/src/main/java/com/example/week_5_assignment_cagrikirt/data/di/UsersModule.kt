package com.example.week_5_assignment_cagrikirt.data.di

import com.example.week_5_assignment_cagrikirt.data.api.ApiServiceUser
import com.example.week_5_assignment_cagrikirt.data.repository.User.UserRepository
import com.example.week_5_assignment_cagrikirt.data.repository.User.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class UsersModule {

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiServiceUser {
        return retrofit.create(ApiServiceUser::class.java)
    }

    @Provides
    fun provideUserRepository(apiServiceUser: ApiServiceUser) : UserRepository {
        return UserRepositoryImpl(apiServiceUser)
    }
}