package com.example.mydisneyapp.ui.characterDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydisneyapp.domain.model.CharacterModel
import com.example.mydisneyapp.domain.usecase.DeleteCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val deleteCharacterUseCase: DeleteCharacterUseCase,
): ViewModel() {

    private val _characters = MutableLiveData<List<CharacterModel>>()
    val characters: LiveData<List<CharacterModel>> get() = _characters

    fun deleteCharacter(characterModel: CharacterModel) {
        viewModelScope.launch {
            try{
                deleteCharacterUseCase.execute(characterModel)
            } catch (e: Exception) {
                Log.e("CharacterListViewModel", "Error deleting character: ${e.message}")
            }
        }
    }
}