package com.lab.petguardian.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.lab.petguardian.data.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(private val authManager: AuthManager) : ViewModel(){



    fun getUser(): FirebaseUser? {
        return authManager.getCurrentUser()
    }

    fun signOut(navigateToWelcomeScreen: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            authManager.signOut()
        }
        navigateToWelcomeScreen()
    }

}

