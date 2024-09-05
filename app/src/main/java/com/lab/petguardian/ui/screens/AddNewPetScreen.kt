package com.lab.petguardian.ui.screens

import android.content.res.Configuration
import android.os.Build

import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.lab.petguardian.DateUtils
import com.lab.petguardian.R
import com.lab.petguardian.ui.common.CommonSelectorButtomItem
import com.lab.petguardian.ui.common.CommonBackButton
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonTextFieldWithTextAbove
import com.lab.petguardian.ui.common.CommonTextTitle
import com.lab.petguardian.ui.theme.Charlotte
import com.lab.petguardian.ui.theme.Geraldine
import com.lab.petguardian.ui.theme.Orchid
import com.lab.petguardian.ui.theme.PetGuardianTheme
import com.lab.petguardian.ui.theme.PowderBlue
import com.lab.petguardian.ui.theme.ShockingPink
import java.text.SimpleDateFormat


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewPetScreen() {
    val namePet by remember { mutableStateOf("") }
    val weight by remember { mutableStateOf("") }
    Scaffold(topBar = {
        CommonBackButton(onClickBackButton = {})
    }) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            item {
                CommonTextTitle("Choose your Pet")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CommonCardImageChoosePet(painter = R.mipmap.cat_choose)
                    CommonCardImageChoosePet(painter = R.mipmap.dog_choose)
                }
                CommonTextFieldWithTextAbove(
                    modifier = Modifier.padding(vertical = 5.dp),
                    textAbove = "Name Pet",
                    placeholderText = "Write the name of your pet",
                    value = namePet,
                    onValueChange = { }
                )
                CommonTextFieldWithTextAbove(
                    modifier = Modifier.padding(vertical = 5.dp),
                    textAbove = "Weight",
                    placeholderText = "Add its weight",
                    value = weight,
                    onValueChange = { }
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp, bottom = 6.dp, top = 5.dp),
                    text = "Date of Birth",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Geraldine,
                )
                DatePickerDocked()
                CommonTextTitle(title = "Choose its gender")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CommonSelectorButtomItem(
                        text = "Male",
                        onClickAddPet = { /*TODO*/ },
                        icon = Icons.Default.Male,
                        color = Charlotte,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(horizontal = 5.dp)
                    )
                    CommonSelectorButtomItem(
                        text = "Female",
                        onClickAddPet = { /*TODO*/ },
                        icon = Icons.Default.Female,
                        color = ShockingPink,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(horizontal = 5.dp)
                    )
                }
                CommonButton(onClick = { /*TODO*/ }, text = "Save")
            }
        }

    }
}

@Composable
fun CommonCardImageChoosePet(@DrawableRes painter: Int, modifier: Modifier? = null) {
    Card(
        modifier = modifier ?: Modifier
            .padding(5.dp)
            .size(130.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(30.dp),
    ) {
        Image(
            modifier = Modifier.clickable(onClickLabel = "Cat") {},
            painter = painterResource(id = painter),
            contentDescription = "choose",
            contentScale = ContentScale.Crop
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val dateToString = datePickerState.selectedDateMillis?.let {
        DateUtils().convertAdjustedMillisToLocalDate(it)
    }?.let { selectedDate ->
        DateUtils().dateToString(selectedDate)
    } ?: "Choose Date"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Geraldine, shape = RoundedCornerShape(12.dp)),
            value = dateToString,
            onValueChange = { },
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date",
                        tint = Geraldine
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            readOnly = true
        )

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text(text = "Cancel")
                }
            }) {
                DatePicker(state = datePickerState, showModeToggle = true)
            }
        }
    }
}
/*

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AddNewPetScreenPreview() {
    PetGuardianTheme {
        AddNewPetScreen()
    }

}*/
