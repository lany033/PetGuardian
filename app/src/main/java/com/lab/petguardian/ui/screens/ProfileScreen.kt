package com.lab.petguardian.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab.petguardian.ui.common.CommonBackButton
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonTextFieldWithTextAbove
import kotlinx.coroutines.launch

@Preview
@Composable
fun ProfileScreen() {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(topBar = {
        CommonTopBackBar(onClickBackButton = {})
    }) { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
                .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RectangleShape
            ) {
                Text(text = "My Profile", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
            CommonTextFieldWithTextAbove(
                textAbove = "Name",
                placeholderText = "Name",
                value = name,
                onValueChange = { name = it }
            )
            CommonTextFieldWithTextAbove(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textAbove = "Last Name",
                placeholderText = "Last Name",
                value = lastName,
                onValueChange = { lastName = it }
            )
            CommonButton(onClick = {

            }, text = "Save")
        }
    }
}

@Composable
fun CommonTopBackBar(onClickBackButton: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 25.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RectangleShape
    ) {
        CommonBackButton(onClickBackButton = { onClickBackButton() })
    }
}