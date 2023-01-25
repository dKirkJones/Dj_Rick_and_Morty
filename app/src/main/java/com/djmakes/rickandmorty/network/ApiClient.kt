package com.djmakes.rickandmorty.network

import com.djmakes.rickandmorty.RickAndMortyService
import com.djmakes.rickandmorty.data.CharacterById
import com.djmakes.rickandmorty.util.SimpleResponse
import retrofit2.Response

class ApiClient(
    private val rickAndMortyService: RickAndMortyService
) {
    suspend fun getCharacterById(characterId: Int): SimpleResponse<CharacterById>{
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }
// This is how we negotiate not having a successful Api call
    private inline fun <T> safeApiCall(apiCall: ()-> Response<T>):SimpleResponse<T>{
        return try {
            // If a network request is to fail this is where it would fail
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception){
            // This when we would call our SimpleResponse object
            SimpleResponse.failure(e)
        }
    }

}
