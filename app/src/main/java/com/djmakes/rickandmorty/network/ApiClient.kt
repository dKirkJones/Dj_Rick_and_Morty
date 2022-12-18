package com.djmakes.rickandmorty.network

import com.djmakes.rickandmorty.RickAndMortyService
import com.djmakes.rickandmorty.data.CharacterById
import retrofit2.Response

class ApiClient(
    private val rickAndMortyService: RickAndMortyService
) {
    suspend fun getCharacterById(characterId: Int): Response<CharacterById>{
        return rickAndMortyService.getCharacterById(characterId)
    }

}
