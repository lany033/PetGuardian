package com.lab.petguardian.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonTextFieldWithTextAbove(textAbove: String, placeholderText:String){
    Card(colors = CardDefaults.cardColors(containerColor = Color.Transparent), shape = RectangleShape) {
        Text(modifier = Modifier.padding(start = 2.dp, bottom = 6.dp), text = textAbove, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(12.dp)),
            placeholder = { Text(text = placeholderText) },
            value = "",
            onValueChange = {},
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
    }
}