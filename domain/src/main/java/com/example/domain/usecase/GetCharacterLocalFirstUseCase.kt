package com.example.mydisneyapp.domain.usecase

import com.example.mydisneyapp.domain.model.CharacterModel
import com.example.mydisneyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterLocalFirstUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend fun execute() : List<CharacterModel> {
        return repository.getCharactersFromDatabase()
    }

}