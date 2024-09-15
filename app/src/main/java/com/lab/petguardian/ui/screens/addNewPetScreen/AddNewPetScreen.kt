package com.lab.petguardian.ui.screens.addNewPetScreen

import android.os.Build
import android.widget.DatePicker
import android.widget.Toast

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab.petguardian.DateUtils
import com.lab.petguardian.R
import com.lab.petguardian.ui.common.CommonBackButton
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonTextFieldWithTextAbove
import com.lab.petguardian.ui.common.CommonTextTitle
import com.lab.petguardian.ui.theme.Geraldine


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNewPetScreen(petViewModel: PetViewModel) {
    val context = LocalContext.current
    var namePet by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var showCheckCat by remember { mutableStateOf(false) }
    var showCheckDog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val radioButtonGenderOptions = listOf("Male", "Female")
    val (selectedGenderOption, onOptionSelectedGender) = remember {
        mutableStateOf(
            radioButtonGenderOptions[0]
        )
    }
    val radioButtonNeuteredOptions = listOf("Yes", "No")
    val (selectedNeuteredOption, onOptionSelectedNeutered) = remember {
        mutableStateOf(
            radioButtonGenderOptions[0]
        )
    }
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
                    CommonCardImageChoosePet(
                        painter = R.mipmap.cat_choose,
                        showCheck = showCheckCat,
                        pet = "Cat",
                        onShowCheck = {
                            showCheckCat = !showCheckCat
                            showCheckDog = false
                        }
                    )
                    CommonCardImageChoosePet(
                        painter = R.mipmap.dog_choose,
                        showCheck = showCheckDog,
                        pet = "Dog",
                        onShowCheck = {
                            showCheckDog = !showCheckDog
                            showCheckCat = false
                        }
                    )
                }
                CommonTextFieldWithTextAbove(
                    modifier = Modifier.padding(vertical = 5.dp),
                    textAbove = "Name Pet",
                    placeholderText = "Write the name of your pet",
                    value = namePet,
                    onValueChange = { namePet = it }
                )
                CommonTextFieldWithTextAbove(
                    modifier = Modifier.padding(vertical = 5.dp),
                    textAbove = "Weight (Kg.)",
                    placeholderText = "Add its weight",
                    value = weight,
                    onValueChange = { weight = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp, bottom = 6.dp, top = 5.dp),
                    text = "Date of Birth",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Geraldine,
                )
                DatePickerDocked(datePickerState)
                CommonTextTitle(title = "Choose its gender")
                Row(
                    modifier = Modifier.selectableGroup(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    radioButtonGenderOptions.forEach { text ->
                        Row(
                            modifier = Modifier.selectable(
                                selected = (text == selectedGenderOption),
                                onClick = { onOptionSelectedGender(text) },
                                role = Role.RadioButton
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedGenderOption),
                                onClick = {
                                    onOptionSelectedGender(text)
                                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                                }
                            )
                            Text(text = text)
                            Icon(
                                imageVector = if (text == "Male") Icons.Default.Male else Icons.Default.Female,
                                contentDescription = "gender"
                            )
                        }
                    }
                }
                CommonTextTitle(title = "Is your pet spayed or neutered?")
                Row(
                    modifier = Modifier.selectableGroup(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    radioButtonNeuteredOptions.forEach { text ->
                        Row(
                            modifier = Modifier.selectable(
                                selected = (text == selectedNeuteredOption),
                                onClick = { onOptionSelectedNeutered(text) },
                                role = Role.RadioButton
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedNeuteredOption),
                                onClick = {
                                    onOptionSelectedNeutered(text)
                                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                                }
                            )
                            Text(text = text)
                        }
                    }
                }
                CommonButton(onClick = {
                    petViewModel.addNewPet(
                        name = namePet,
                        type = showCheckCat,
                        weight = weight.toDouble(),
                        neutered = selectedNeuteredOption,
                        gender = selectedGenderOption,
                        dateOfBirth = datePickerState.selectedDateMillis
                    )
                }, text = "Save")
            }
        }

    }
}

@Composable
fun CommonCardImageChoosePet(
    @DrawableRes painter: Int,
    modifier: Modifier? = null,
    pet: String,
    showCheck: Boolean,
    onShowCheck: (Boolean) -> Unit
) {
    Card(
        modifier = modifier ?: Modifier
            .padding(5.dp)
            .size(130.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(30.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.clickable(onClickLabel = pet) { onShowCheck(showCheck) },
                painter = painterResource(id = painter),
                contentDescription = "choose",
                contentScale = ContentScale.Crop
            )
            if (showCheck) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f))
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(60.dp),
                            imageVector = Icons.Default.Check,
                            contentDescription = "check",
                            tint = Color.Green
                        )
                        Text(
                            text = pet,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(datePickerState: DatePickerState) {
    var showDatePicker by remember { mutableStateOf(false) }


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
