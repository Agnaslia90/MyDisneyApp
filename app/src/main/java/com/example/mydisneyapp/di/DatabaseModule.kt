package com.example.mydisneyapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.CharacterDao
import com.example.data.local.MyDisneyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MyDisneyDB {
        return Room.databaseBuilder(
            context,
            MyDisneyDB::class.java,
            "my_disney_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCharacterDao(
        database: MyDisneyDB
    ): CharacterDao {
        return database.characterDao()
    }
}

