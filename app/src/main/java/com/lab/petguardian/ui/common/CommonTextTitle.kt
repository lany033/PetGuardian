package com.lab.petguardian.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CommonTextTitle(title: String){
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}

