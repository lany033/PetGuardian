package com.lab.petguardian.ui.screens.authScreens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab.petguardian.data.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val authManager: AuthManager) : ViewModel(){

    fun sendResetPassword(email: String, backLogin:() -> Unit){
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    authManager.resetPassword(email)
                    backLogin()
                }
            }catch (e: Exception){
                Log.i("ForgotPasswordError", e.message.orEmpty())
            }
        }
    }
}