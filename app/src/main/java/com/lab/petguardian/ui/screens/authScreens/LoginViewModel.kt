package com.lab.petguardian.ui.screens.authScreens

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import com.lab.petguardian.R
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

    fun signInWithGoogle(googleSignInLauncher: ActivityResultLauncher<Intent>, context: Context) {

        val googleSignInClient: GoogleSignInClient by lazy {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            GoogleSignIn.getClient(context, gso)
        }

        val signInIntent = googleSignInClient.signInIntent
        try {
            googleSignInLauncher.launch(signInIntent)
            Log.d("LoginViewModel", "Launching Google Sign-In")
        } catch (e: Exception) {
            Log.e("GoogleSignIn", "Error launching Google Sign-In", e)
        }

    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>): AuthRes<GoogleSignInAccount>? {
        return try {
            val account = task.getResult(ApiException::class.java)
            AuthRes.Success(account)
        } catch (e: ApiException) {
            AuthRes.Error(e.message ?: "Google sign-in failed.")

        }
    }

    fun loginWithGoogle(
        activityResult: ActivityResult,
        onGoogleSignIn: () -> Unit,
        context: Context
    ) {
        val account = handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(activityResult.data))
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
                            Log.d("LoginViewModel", "User logged in with Google: $fireUser")
                        }
                    } catch (e: Exception) {
                        AuthRes.Error(e.message ?: "Ocurrio un error")
                    }
                }
            }

            is AuthRes.Error -> {
                Toast.makeText(
                    context,
                    "Error LoginGoogle: ${account.errorMessage}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("LoginViewModel", "Error LoginGoogle: ${account.errorMessage}")
            }

            else -> {
                Toast.makeText(context, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        }
    }


//    suspend fun signInWithGoogleCredential(credential: AuthCredential): AuthRes<FirebaseUser>? {
//        return try {
//            val firebaseUser = auth.signInWithCredential(credential).await()
//            firebaseUser.user?.let {
//                AuthRes.Success(it)
//            } ?: throw Exception("Sign in with Google failed.")
//        } catch (e: Exception) {
//            AuthRes.Error(e.message ?: "Sign in with Google failed.")
//        }
//    }
//
//    fun signInWithGoogle(googleSignInLauncher: ActivityResultLauncher<Intent>) {
//        val signInIntent = googleSignInClient.signInIntent
//        googleSignInLauncher.launch(signInIntent)
//    }


}