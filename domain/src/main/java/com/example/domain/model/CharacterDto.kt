package com.example.mydisneyapp.domain.model

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("_id")
    val id: Int,
    val name: String,
    @SerializedName("imageUrl") val imageUrl: String,
)
