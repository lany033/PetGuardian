package com.lab.petguardian.screens

import android.provider.ContactsContract.CommonDataKinds
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = "back",
            modifier = Modifier.size(35.dp)
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RectangleShape
        ) {
            Text(text = "Create Account", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = "Register with your E-mail address")
        }
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RectangleShape
        ) {
            Text(
                modifier = Modifier.padding(start = 2.dp, bottom = 6.dp),
                text = "ADD AN E-MAIL ADDRESS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(12.dp)),
                placeholder = { Text(text = "E-mail address") },
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
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RectangleShape
        ) {
            Text(
                modifier = Modifier.padding(start = 2.dp, bottom = 6.dp),
                text = "CREATE PASSWORD",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(12.dp)),
                placeholder = { Text(text = "Password") },
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
        CommonButton(onClick = { /*TODO*/ }, text = "Sign Up", color = Color.Blue)
    }
}


@Composable
fun CommonButton(onClick: () -> Unit, text: String, color: Color) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 10.dp, bottom = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = text, fontSize = 15.sp)
        }
    }
}

