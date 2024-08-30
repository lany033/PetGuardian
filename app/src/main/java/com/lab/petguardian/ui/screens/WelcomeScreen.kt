package com.lab.petguardian.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.data.AuthRes
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonHorizontalPagerHomeScreen
import com.lab.petguardian.ui.common.CommonLoginBottomSheet
import kotlinx.coroutines.launch

private suspend fun emailPassSignIn(
    email: String,
    password: String,
    authManager: AuthManager,
    context: Context,
    onClickSignIn: () -> Unit
) {
    if (email.isNotEmpty() && password.isNotEmpty()) {
        when (val result = authManager.signInWithEmailAndPassword(email, password)) {
            is AuthRes.Success -> {
                onClickSignIn()
            }

            is AuthRes.Error -> {
                Toast.makeText(context, "Error SignUp: ${result.errorMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    } else {
        Toast.makeText(context, "Existen campos vacios", Toast.LENGTH_SHORT).show()
    }
}
@Composable
fun WelcomeScreen(
    onClickSignUp: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickHome: () -> Unit,
    onGoogleSignIn: () -> Unit,
    authManager: AuthManager,
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    var context = LocalContext.current

    var scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    val googleSignInLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()){ result ->
        when(val account = authManager.handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(result.data))){
            is AuthRes.Success -> {
                val credential = GoogleAuthProvider.getCredential(account?.data?.idToken, null)
                scope.launch {
                    val fireUser = authManager.signInWithGoogleCredential(credential)
                    if(fireUser != null){
                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                        onGoogleSignIn()
                    }
                }
                Log.d("USER LOGIN","${authManager.getCurrentUser()}")
            }
            is AuthRes.Error -> {
                Toast.makeText(context, "Error: ${account.errorMessage}", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Error: Unknown", Toast.LENGTH_SHORT).show()
            }
        }
    }



    if (showBottomSheet) {
        CommonLoginBottomSheet(
            onDismiss = { showBottomSheet = false },
            onClickSignUp = { onClickSignUp() },
            onClickForgotPassword = { onClickForgotPassword() },
            onClickHome = { scope.launch { emailPassSignIn(email, password, authManager, context, onClickHome) } },
            onGoogleSignIn = { authManager.signInWithGoogle(googleSignInLauncher) },
            email = email,
            password = password,
            onValueEmailChange = { email = it},
            onValuePasswordChange = { password = it},
        )
    }
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerppading ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerppading)
        ) {
            CommonHorizontalPagerHomeScreen(Modifier.align(Alignment.Center))
            CommonButton(
                onClick = { showBottomSheet = true },
                text = "Get Started",
                modifier = Modifier
                    .align(
                        Alignment.BottomCenter
                    )
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            )
        }
    }

}

/*
@Preview
@Composable
fun LoginScreenPreview(){
    PetGuardianTheme {
        LoginScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreviewNight(){
    PetGuardianTheme {
        LoginScreen()
    }
}*/
