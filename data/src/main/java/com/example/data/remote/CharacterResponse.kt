package com.example.data.remote

import com.example.mydisneyapp.domain.model.CharacterDto
import com.google.gson.annotations.SerializedName

data class CharacterResponse (
    @SerializedName("data")
    val characters: List<CharacterDto>
)