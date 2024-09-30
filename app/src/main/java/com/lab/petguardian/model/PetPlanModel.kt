package com.lab.petguardian.model

data class PetPlanModel(
    val id: String,
    val namePet: String,
    val date: String,
    val description: String,
    val isCompleted: Boolean
)