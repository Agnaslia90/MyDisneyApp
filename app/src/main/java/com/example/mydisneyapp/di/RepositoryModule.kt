package com.example.mydisneyapp.di

import com.example.data.local.CharacterDao
import com.example.data.mapper.CharacterDtoMapper
import com.example.data.mapper.CharacterEntityMapper
import com.example.data.remote.DisneyApiService
import com.example.data.repository.CharacterRepositoryImpl
import com.example.mydisneyapp.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCharacterRepository(
        api: DisneyApiService,
        characterDao: CharacterDao
    ): CharacterRepository {
        return CharacterRepositoryImpl(api, characterDao,
            characterEntityMapper = CharacterEntityMapper(),
            characterDtoMapper = CharacterDtoMapper()
        )
    }
}
