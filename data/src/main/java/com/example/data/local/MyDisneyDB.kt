package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 3)
abstract class MyDisneyDB: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}