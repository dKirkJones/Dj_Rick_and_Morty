package com.djmakes.rickandmorty.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djmakes.rickandmorty.data.CharacterById
import com.djmakes.rickandmorty.repository.SharedRepository
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<CharacterById?>()
    val characterByIdLiveData: LiveData<CharacterById?> = _characterByIdLiveData

    fun refreshCharacter(characterId: Int){
        viewModelScope.launch {
            val resonse = repository.getCharacterById(characterId)

            _characterByIdLiveData.postValue(resonse)
        }

    }

}