package com.lab.petguardian.ui.screens.authScreens

import android.app.Activity
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
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.lab.petguardian.data.AuthRes
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonHorizontalPagerHomeScreen
import com.lab.petguardian.ui.common.CommonLoginBottomSheet
import kotlinx.coroutines.launch


@Composable
fun WelcomeScreen(
    onClickSignUp: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickHome: () -> Unit,
    onGoogleSignIn: () -> Unit,
    loginViewModel: LoginViewModel
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    val googleSignInLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()){ result ->
        loginViewModel.loginWithGoogle(activityResult = result, onGoogleSignIn = { onGoogleSignIn() }, context = context)
    }

    if (showBottomSheet) {
        CommonLoginBottomSheet(
            onDismiss = { showBottomSheet = false },
            onClickSignUp = { onClickSignUp() },
            onClickForgotPassword = { onClickForgotPassword() },
            onClickHome = { loginViewModel.login(email, password, { onClickHome() } , context) },
            onGoogleSignIn = { loginViewModel.signInWithGoogle(googleSignInLauncher) },
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

