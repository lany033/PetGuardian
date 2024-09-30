package com.lab.petguardian.data

import com.google.firebase.Timestamp

data class PetPlanDto(
    val namePet: String,
    val date: Timestamp,
    val description: String,
    val isCompleted: Boolean
)
