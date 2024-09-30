package com.lab.petguardian.ui.screens.addNewPetScreen

import android.os.Build
import android.widget.Toast

import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lab.petguardian.R
import com.lab.petguardian.ui.common.CommonTextButtonWithIcon
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonCardImageChoosePet
import com.lab.petguardian.ui.common.CommonTextFieldWithTextAbove
import com.lab.petguardian.ui.common.CommonTextTitle
import com.lab.petguardian.ui.common.CommonDatePickerDocked
import com.lab.petguardian.ui.theme.Geraldine


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNewPetScreen(onClickBackHome: () -> Unit) {

    val petViewModel: AddNewPetViewModel = hiltViewModel()
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
    val addPetState by petViewModel.addPetState.collectAsState()

    LaunchedEffect(addPetState) {
        addPetState.let { state ->
            if (state.isSuccessful){
                onClickBackHome()
            } else {
                Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(topBar = {
        CommonTextButtonWithIcon(onClickBackButton = { onClickBackHome() })
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
                CommonDatePickerDocked(datePickerState)
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
                        weight = if(weight.isNotEmpty())weight.toDouble() else 0.0,
                        neutered = selectedNeuteredOption,
                        gender = selectedGenderOption,
                        dateOfBirth = datePickerState.selectedDateMillis
                    )
                }, text = "Save")
            }
        }

    }
}

