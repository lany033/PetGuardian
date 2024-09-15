package com.lab.petguardian.data

import com.google.firebase.Timestamp

data class PetDto(
    val name: String,
    val type: String,
    val weight: Double,
    val neutered: String,
    val gender: String,
    val dateOfBirth: Timestamp
)
