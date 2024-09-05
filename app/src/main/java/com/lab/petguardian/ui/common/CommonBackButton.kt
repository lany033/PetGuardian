package com.lab.petguardian.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab.petguardian.R

@Composable
fun CommonBackButton(modifier: Modifier? = null, onClickBackButton: () -> Unit){
    Row(modifier =  modifier ?: Modifier.fillMaxWidth().padding(vertical = 25.dp), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { onClickBackButton() }) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "back",
                modifier = Modifier.size(30.dp)
            )
        }
        Text(text = "Back", fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}