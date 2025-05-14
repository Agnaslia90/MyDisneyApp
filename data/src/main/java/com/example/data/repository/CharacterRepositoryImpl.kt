package com.example.data.repository

import android.util.Log
import com.example.data.local.CharacterDao
import com.example.data.mapper.CharacterDtoMapper
import com.example.data.mapper.CharacterEntityMapper
import com.example.data.remote.DisneyApiService
import com.example.mydisneyapp.domain.model.CharacterModel
import com.example.mydisneyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: DisneyApiService,
    private val characterDao: CharacterDao,
    private val characterEntityMapper: CharacterEntityMapper,
    private val characterDtoMapper: CharacterDtoMapper
): CharacterRepository {

    override suspend fun getAllCharacters(): List<CharacterModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllCharacters()
                Log.d("CharacterRepository", "API Response: ${response.code()}")

                if (response.isSuccessful) {
                    val characters = response.body()?.characters?.map { characterDto ->
                        characterDtoMapper.map(characterDto)
                    } ?: emptyList()
                    Log.d("CharacterRepository", "Characters retrieved: ${characters.size}")
                    saveCharactersToDatabase(characters)
                    return@withContext characters
                } else {
                    Log.e(
                        "CharacterRepository",
                        "API call failed: ${response.errorBody()?.string()}"
                    )
                    return@withContext getCharactersFromDatabase()
                }
            } catch (e: Exception) {
                Log.e("CharacterRepository", "Exception during API call", e)
                return@withContext getCharactersFromDatabase()
            }
        }
    }

    override suspend fun getCharactersFromDatabase(): List<CharacterModel> {
        return withContext(Dispatchers.IO) {
            val characterEntities = characterDao.getAllCharacters()

            characterEntities.map { entity ->
               characterEntityMapper.map(entity)
            }
        }
    }

    override suspend fun saveCharactersToDatabase(characterModels: List<CharacterModel>) {
        val entities = characterModels.map { model ->
            characterEntityMapper.unmap(model)
        }
        characterDao.insertAll(entities)
    }

    override suspend fun deleteCharacter(characterModel: CharacterModel) {
        characterDao.deleteCharacter(characterModel.id)
    }

    override suspend fun getCharacterLocalFirst(): List<CharacterModel> {
        return try {
            val response = api.getAllCharacters()
            if (response.isSuccessful) {
                val characters = response.body()?.characters?.map { characterDto ->
                    characterDtoMapper.map(characterDto)
                } ?: emptyList()

                saveCharactersToDatabase(characters)
                characters
            } else {
                Log.e("CharacterRepository", "API error: ${response.errorBody()?.string()}")
                getCharactersFromDatabase()
            }
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error fetching from API", e)
            getCharactersFromDatabase()
        }
    }
}