package com.lab.petguardian.model

data class PetPlanModel(
    val id: String,
    val title: String,
    val date: String,
    val description: String,
    val isCompleted: Boolean,
    val petId: String,
    val petName: String
)