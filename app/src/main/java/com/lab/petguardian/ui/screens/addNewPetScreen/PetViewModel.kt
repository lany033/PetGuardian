package com.lab.petguardian.ui.screens.addNewPetScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.lab.petguardian.data.DatabaseRepository
import com.lab.petguardian.data.PetDto
import com.lab.petguardian.model.PetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) : ViewModel() {
    var _petState = MutableStateFlow(PetUiState())
    val petState: StateFlow<PetUiState> = _petState

    fun addNewPet(
        name: String,
        type: Boolean,
        dateOfBirth: Long?,
        weight: Double,
        neutered: String,
        gender: String
    ) {
        val dto = prepareDTO(name, type, dateOfBirth, weight, neutered, gender)
        if (dto != null){
            viewModelScope.launch {
                databaseRepository.addPet(dto)
            }
        }
    }

    private fun prepareDTO(
        name: String,
        type: Boolean,
        dateOfBirth: Long?,
        weight: Double,
        neutered: String,
        gender: String
    ): PetDto? {
        if (name.isBlank()) return null
        val timeStamp = if (dateOfBirth != null) {
            val seconds = dateOfBirth / 1000
            val nanoseconds = ((dateOfBirth % 1000) * 1000000).toInt()
            Timestamp(seconds, nanoseconds)
        } else {
            Timestamp.now()
        }

        val typePet = if (type) {
             "Cat"
        } else {
            "Dog"
        }

        return try {
            PetDto(name, typePet, weight, neutered, gender, timeStamp)
        } catch (e: Exception) {
            null
        }
    }

}


data class PetUiState(
    val isLoading: Boolean = false,
    val pet: List<PetModel> = emptyList()
)
