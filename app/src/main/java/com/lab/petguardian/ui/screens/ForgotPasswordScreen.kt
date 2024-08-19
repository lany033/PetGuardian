package com.lab.petguardian.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab.petguardian.ui.common.CommonBackButton
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonTextFieldWithTextAbove

@Preview
@Composable
fun ForgotPasswordScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        CommonBackButton(onClickBackButton = {})
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RectangleShape
        ) {
            Text(text = "Reset Password", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = "Enter the email associate with your account and we'll send an email with instructions to reset your password")
        }
        CommonTextFieldWithTextAbove(textAbove = "ADD AN E-MAIL ADDRESS", placeholderText = "E-mail address")

        CommonButton(onClick = { /*TODO*/ }, text = "Send email", color = Color.Blue, modifier = Modifier)
    }
}