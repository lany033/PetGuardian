package com.lab.petguardian.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.data.AuthRes
import com.lab.petguardian.ui.common.CommonBackButton
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonTextFieldWithTextAbove
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navigation: NavController, authManager: AuthManager) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
                .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            CommonBackButton(onClickBackButton = {})
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RectangleShape
            ) {
                Text(text = "Create Account", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Text(text = "Register with your E-mail address")
            }
            CommonTextFieldWithTextAbove(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                textAbove = "ADD AN E-MAIL ADDRESS",
                placeholderText = "E-mail address",
                value = email,
                onValueChange = { email = it }
            )
            CommonTextFieldWithTextAbove(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textAbove = "CREATE PASSWORD",
                placeholderText = "Password",
                value = password,
                onValueChange = { password = it }
            )
            CommonButton(onClick = {
                                   scope.launch {
                                       signUp(email, password, authManager, context, navigation)
                                   }
            }, text = "Sign Up")
        }
    }

}

private suspend fun signUp(email: String, password: String, authManager: AuthManager, context: Context, navigation: NavController) {
    if (email.isNotEmpty() && password.isNotEmpty()) {
        when(val result = authManager.createUserWithEmailAndPassword(email, password)){
            is AuthRes.Success -> {
                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                navigation.popBackStack()
            }
            is AuthRes.Error -> {
                Toast.makeText(context, "Error: ${result.errorMessage}", Toast.LENGTH_LONG).show()
            }
        }
    } else {
        Toast.makeText(context, "Existen campos vacios", Toast.LENGTH_SHORT).show()
    }
}



/*
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SignUpLightPreview() {
    PetGuardianTheme {
        SignUpScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignUpPreviewDark() {
    PetGuardianTheme {
        SignUpScreen()
    }
}*/




