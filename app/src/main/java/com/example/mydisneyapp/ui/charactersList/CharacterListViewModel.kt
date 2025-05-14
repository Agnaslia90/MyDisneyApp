package com.example.mydisneyapp.ui.charactersList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydisneyapp.domain.model.CharacterModel
import com.example.mydisneyapp.domain.usecase.GetAllCharactersUseCase
import com.example.mydisneyapp.domain.usecase.GetCharacterLocalFirstUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterLocalFirstUseCase: GetCharacterLocalFirstUseCase,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
): ViewModel() {

    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characters: LiveData<List<CharacterModel>> get() = _characters

    fun loadCharactersLocalFirst(){
        viewModelScope.launch {
            val localCharacters = getCharacterLocalFirstUseCase.execute()
            _characters.postValue(localCharacters)
        }
    }

    fun recoverCharacters() {
        viewModelScope.launch {
            val recoverCharacters = getAllCharactersUseCase.execute()
            _characters.postValue(recoverCharacters)
        }
    }
}