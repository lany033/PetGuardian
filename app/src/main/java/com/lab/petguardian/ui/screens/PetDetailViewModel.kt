package com.lab.petguardian.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab.petguardian.data.DatabaseRepository
import com.lab.petguardian.model.PetPlanModel
import com.lab.petguardian.petAge
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) :
    ViewModel() {

    private var _petDetailState = MutableStateFlow(PetDetailState())
    val petDetailState: StateFlow<PetDetailState> = _petDetailState


    @RequiresApi(Build.VERSION_CODES.O)
    fun getPetById(id: String) {
        viewModelScope.launch {
            getPlansByUser(id)
            val petDetail = databaseRepository.getPetById(id)
            petDetail.collect { petModel ->
                try {
                    _petDetailState.update {
                        it.copy(
                            namePet = petModel?.name ?: "No name",
                            gender = petModel?.gender ?: "No gender",
                            type = petModel?.type ?: "No type",
                            weight = petModel?.weight ?: 0.0,
                            ages = petModel?.dateOfBirth?.let { it1 -> petAge(it1) } ?: "0"
                        )
                    }
                } catch (e: Exception) {
                    println(e)
                }

            }
        }

    }

    private fun getPlansByUser(id: String){
        viewModelScope.launch {
            databaseRepository.getPlans().collect{ petPlanList ->
                _petDetailState.update {
                    it.copy(
                        planList = petPlanList.filter { it.petId == id }
                    )
                }
            }
        }
    }

}


data class PetDetailState(
    val isLoading: Boolean = false,
    val namePet: String = "",
    val gender: String = "",
    val type: String = "",
    val weight: Double = 0.0,
    val ages: String = "",
    val planList: List<PetPlanModel> = emptyList()
)