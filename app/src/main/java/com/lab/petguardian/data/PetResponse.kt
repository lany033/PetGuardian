package com.lab.petguardian.data

import com.google.firebase.Timestamp

data class PetResponse(
    val id: String? = null,
    val name: String? = null,
    val type: String? = "Cat",
    val weight: Double? = null,
    val neutered: String? = "No",
    val gender: String? = "Male",
    val dateOfBirth: Timestamp? = null,
)
