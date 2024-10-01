package com.lab.petguardian.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lab.petguardian.ui.common.CommonTextButtonWithIcon
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonDatePickerDocked
import com.lab.petguardian.ui.common.CommonTextFieldWithTextAbove

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlanScreen() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val addPlanScreenViewModel: AddPlanScreenViewModel = hiltViewModel()

    val datePickerState = rememberDatePickerState()

    Scaffold(topBar = { CommonTextButtonWithIcon(onClickBackButton = { }) }) { it ->
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
                Text(text = "Add New Plan", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
            CommonTextFieldWithTextAbove(
                textAbove = "Title",
                placeholderText = "Title",
                value = title,
                onValueChange = { title = it }
            )
            CommonTextFieldWithTextAbove(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textAbove = "Description",
                placeholderText = "Description",
                value = description,
                onValueChange = { description = it }
            )
            CommonDatePickerDocked(datePickerState = datePickerState)
            CommonButton(onClick = {
                addPlanScreenViewModel.addPlan(
                    name = title,
                    description = description,
                    date = datePickerState.selectedDateMillis,
                    isCompleted = false
                )
            }, text = "Save")

        }

    }
}

