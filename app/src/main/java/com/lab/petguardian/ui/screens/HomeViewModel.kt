package com.lab.petguardian.ui.screens

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
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

    private fun getSignInClient(context: Context): SignInClient {
        return Identity.getSignInClient(context)
    }

    fun signOut(navigateToWelcomeScreen: () -> Unit, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            authManager.signOut(getSignInClient(context))
        }
        navigateToWelcomeScreen()
    }

}

