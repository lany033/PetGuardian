package com.lab.petguardian.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab.petguardian.data.DatabaseRepository
import com.lab.petguardian.preparePetPlanDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlanScreenViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
): ViewModel(){

    private var _addPlanState = MutableStateFlow(AddPlanState())
    val addPlanState: StateFlow<AddPlanState> = _addPlanState

    fun addPlan(
        name: String,
        description: String,
        date: Long?,
        isCompleted: Boolean
    ){
        viewModelScope.launch {
            val petPlanDto = preparePetPlanDTO(name, date, description, isCompleted)
            if (petPlanDto != null) {
                databaseRepository.addPlanByPet(petPlanDto)
            }
        }
    }

}

data class AddPlanState(
    val error: String = "",
    val isLoading: Boolean = false
)