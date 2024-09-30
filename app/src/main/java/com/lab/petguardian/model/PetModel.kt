package com.lab.petguardian.model

data class PetModel(
    val id: String,
    val name: String,
    val type: String,
    val weight: Double,
    val neutered: String,
    val gender: String,
    val dateOfBirth: String
)
