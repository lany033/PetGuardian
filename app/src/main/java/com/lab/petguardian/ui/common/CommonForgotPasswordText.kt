package com.lab.petguardian.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.lab.petguardian.ui.theme.Geraldine

@Composable
fun CommonForgotPasswordText(onClickForgotPassword: () -> Unit) {
    Text(
        modifier = Modifier.clickable { onClickForgotPassword() }, text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        color = Geraldine
    )
}