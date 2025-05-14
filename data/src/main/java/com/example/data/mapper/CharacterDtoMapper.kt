package com.example.data.mapper

import com.example.mydisneyapp.domain.model.CharacterDto
import com.example.mydisneyapp.domain.model.CharacterModel
import javax.inject.Inject

class CharacterDtoMapper @Inject constructor(){

    fun map(characterDto: CharacterDto): CharacterModel {
        return CharacterModel(
            id = characterDto.id,
            name = characterDto.name,
            imageUrl = characterDto.imageUrl
        )
    }

    fun unmap(characterModel: CharacterModel): CharacterDto {
        return CharacterDto(
            id = characterModel.id,
            name = characterModel.name,
            imageUrl = characterModel.imageUrl.toString()
        )
    }
}