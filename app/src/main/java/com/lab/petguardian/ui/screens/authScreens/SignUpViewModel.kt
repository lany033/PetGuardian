package com.lab.petguardian.ui.screens.authScreens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab.petguardian.data.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authManager: AuthManager) : ViewModel(){

    fun createAccountWithEmailAndPassword(email: String, password: String, navigateToWelcomeScreen: () -> Unit) {
        viewModelScope.launch {

            try {

                val result = withContext(Dispatchers.IO) {
                    authManager.createUserWithEmailAndPassword(email, password)
                }

                if (result != null) {
                    navigateToWelcomeScreen()
                } else {
                    //error
                    Log.i("aris", "errorrrrr")
                }

            } catch (e: Exception) {
                //error
                Log.i("aris", e.message.orEmpty())
            }

        }
    }
}