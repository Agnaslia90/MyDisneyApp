package com.example.mydisneyapp.domain.repository

import com.example.mydisneyapp.domain.model.CharacterModel

interface CharacterRepository{

    suspend fun getAllCharacters(): List<CharacterModel>
    suspend fun getCharactersFromDatabase(): List<CharacterModel>
    suspend fun saveCharactersToDatabase(characterModels: List<CharacterModel>)
    suspend fun deleteCharacter(characterModel: CharacterModel)
    suspend fun getCharacterLocalFirst(): List<CharacterModel>
}