package com.example.data.mapper

import com.example.data.local.CharacterEntity
import com.example.mydisneyapp.domain.model.CharacterModel
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor(){

    fun map(characterEntity: CharacterEntity): CharacterModel {
        return CharacterModel(
            id = characterEntity.id.toInt(),
            name = characterEntity.name,
            imageUrl = characterEntity.imageUrl
        )
    }

    fun unmap(characterModel: CharacterModel): CharacterEntity {
        return CharacterEntity(
            id = characterModel.id,
            name = characterModel.name,
            imageUrl = characterModel.imageUrl.toString()
        )
    }
}