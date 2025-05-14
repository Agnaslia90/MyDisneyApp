package com.example.mydisneyapp.di

import com.example.data.remote.DisneyApiService
import com.example.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideDisneyApiService(): DisneyApiService {
        return RetrofitClient.disneyApiService
    }
}