package com.lab.petguardian.ui.screens.authScreens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.data.AuthRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authManager: AuthManager) : ViewModel() {

    fun login(email: String, password: String, navigateToHome: () -> Unit, context: Context) {
        viewModelScope.launch {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                try {
                    val result = withContext(Dispatchers.IO) {
                        authManager.signInWithEmailAndPassword(email, password)
                    }
                    if (result != null) {
                        navigateToHome()
                    }
                } catch (e: Exception) {
                    AuthRes.Error(e.message ?: "Ocurrio un error")
                }
            } else {
                Toast.makeText(context, "Existen campos vacios", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signInWithGoogle(googleSignInLauncher: ActivityResultLauncher<Intent>) {
        val signInIntent = authManager.googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    fun loginWithGoogle(
        activityResult: ActivityResult,
        onGoogleSignIn: () -> Unit,
        context: Context
    ) {
        val account =
            authManager.handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(activityResult.data))
        when (account) {
            is AuthRes.Success -> {
                val credential = GoogleAuthProvider.getCredential(account.data.idToken, null)
                viewModelScope.launch {
                    try {
                        withContext(Dispatchers.IO) {
                            val fireUser = authManager.signInWithGoogleCredential(credential)
                            if (fireUser != null) {
                                onGoogleSignIn()
                            }
                        }
                    } catch (e: Exception) {
                        AuthRes.Error(e.message ?: "Ocurrio un error")
                    }
                }
            }

            is AuthRes.Error -> {
                Toast.makeText(context, account.errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {
                Toast.makeText(context, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        }

    }

}