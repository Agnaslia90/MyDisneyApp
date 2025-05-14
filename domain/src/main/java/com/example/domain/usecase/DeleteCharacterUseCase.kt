package com.example.mydisneyapp.domain.usecase

import com.example.mydisneyapp.domain.model.CharacterModel
import com.example.mydisneyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class DeleteCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository,
) {
    suspend fun execute(characterModel: CharacterModel) {
        val characterId = characterModel.id
        val character = characterModel.copy(id = characterId)
        repository.deleteCharacter(character)
    }
}