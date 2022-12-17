package com.djmakes.rickandmorty

import com.djmakes.rickandmorty.data.CharacterById
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {
    @GET("character/{character-id}")
    fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Call<CharacterById>

}