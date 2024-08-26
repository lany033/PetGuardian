package com.lab.petguardian.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonBackButton(modifier: Modifier? = null, onClickBackButton: () -> Unit){
    Row(modifier = modifier?.fillMaxWidth() ?: Modifier, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { onClickBackButton() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = "back",
                modifier = Modifier.size(30.dp)
            )
        }
        Text(text = "Back", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}