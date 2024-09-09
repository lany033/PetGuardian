package com.lab.petguardian.data

import com.google.firebase.Timestamp

data class PetResponse(
    val id: String? = null,
    val name: String? = null,
    val type: String? = null,
    val weight: Double? = null,
    val neutered: Boolean = false,
    val gender: String? = null,
    val dateOfBirth: Timestamp? = null,
)
