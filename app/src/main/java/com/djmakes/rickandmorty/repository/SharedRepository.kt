package com.djmakes.rickandmorty.repository

import com.djmakes.rickandmorty.data.CharacterById
import com.djmakes.rickandmorty.network.NetworkLayer
import retrofit2.Response

class SharedRepository {

    suspend fun getCharacterById(characterId: Int) : CharacterById? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed){
            return null
        }
        if (!request.isSuccessful){
            return null
        }
        return request.body
    }
}