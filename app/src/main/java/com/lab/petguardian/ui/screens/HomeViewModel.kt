package com.lab.petguardian.ui.screens

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseUser
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.data.DatabaseRepository
import com.lab.petguardian.model.PetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authManager: AuthManager,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private var _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        getPetListByUser()
    }


    fun getUser(): FirebaseUser? {
        Log.d("HomeViewModel", authManager.getCurrentUser().toString())
        return authManager.getCurrentUser()
    }

    private fun getSignInClient(context: Context): SignInClient {
        return Identity.getSignInClient(context)
    }

    fun signOut(navigateToWelcomeScreen: () -> Unit, context: Context) {
        viewModelScope.launch {
            authManager.signOut(getSignInClient(context))
        }
        navigateToWelcomeScreen()
    }

    private fun getPetListByUser() {
        viewModelScope.launch {
            databaseRepository.getPets().collect { petList ->
                _homeUiState.update { homeUiState ->
                    homeUiState.copy(petListByUser = petList)
                }

            }
        }
    }

}

data class HomeUiState(
    val isLoading: Boolean = false,
    val petListByUser: List<PetModel> = emptyList()
)

