package com.example.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface DisneyApiService {

    @GET ("character")
    suspend fun getAllCharacters() : Response<CharacterResponse>
    }
