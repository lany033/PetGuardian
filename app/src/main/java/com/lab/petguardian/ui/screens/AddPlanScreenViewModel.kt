package com.lab.petguardian.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab.petguardian.data.DatabaseRepository
import com.lab.petguardian.model.PetModel
import com.lab.petguardian.preparePetPlanDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlanScreenViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private var _addPlanState = MutableStateFlow(AddPlanState())
    val addPlanState: StateFlow<AddPlanState> = _addPlanState

    fun getPetById(petId: String) {
        viewModelScope.launch {
            databaseRepository.getPetById(petId).collect { petModel ->
                if (petModel != null) {
                    _addPlanState.update { addPlanState -> addPlanState.copy(namePet = petModel.name) }
                }
            }
        }
    }

    fun addPlan(
        petName: String,
        titlePlan: String,
        description: String,
        date: Long?,
        isCompleted: Boolean,
        petId: String
    ) {
        viewModelScope.launch {
            val petPlanDto =
                preparePetPlanDTO(petName, titlePlan, date, description, isCompleted, petId)
            if (petPlanDto != null) {
                databaseRepository.addPlanByPet(petPlanDto)
            }
        }
    }

}

data class AddPlanState(
    val namePet: String = "",
    val error: String = "",
    val isLoading: Boolean = false
)