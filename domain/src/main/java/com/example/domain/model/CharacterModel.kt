package com.example.mydisneyapp.domain.model


data class CharacterModel (
    val id: Int,
    val name: String,
    val imageUrl: String? = null,
)