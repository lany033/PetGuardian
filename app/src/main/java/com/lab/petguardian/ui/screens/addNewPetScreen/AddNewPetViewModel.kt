package com.lab.petguardian.ui.screens.addNewPetScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab.petguardian.data.AuthRes
import com.lab.petguardian.data.DatabaseRepository
import com.lab.petguardian.model.PetModel
import com.lab.petguardian.prepareDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewPetViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) :
    ViewModel() {

    private var _addPetState = MutableStateFlow(AddPetUiState())
    val addPetState: StateFlow<AddPetUiState> = _addPetState

    fun addNewPet(
        name: String,
        type: Boolean,
        dateOfBirth: Long?,
        weight: Double,
        neutered: String,
        gender: String,

    ) {
        viewModelScope.launch {
            if (name.isNotEmpty()) {
                try {
                    val dto = prepareDTO(name, type, dateOfBirth, weight, neutered, gender)
                    if (dto != null) {
                        databaseRepository.addPet(dto)
                        _addPetState.value = AddPetUiState(isSuccessful = true)
                    }
                } catch (e: Exception) {
                    AuthRes.Error(e.message ?: " Add Pet : Ocurrio un error")
                }
            } else {
                _addPetState.value = AddPetUiState(error = "El nombre no puede estar vacio")
            }
        }
    }
}

data class AddPetUiState(
    val isLoading: Boolean = false,
    val pet: List<PetModel> = emptyList(),
    var error: String = "",
    val isSuccessful: Boolean = false
)
