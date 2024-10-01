package com.lab.petguardian.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.local.ReferenceSet

data class PetPlanDto(
    val namePet: String,
    val date: Timestamp,
    val description: String,
    val isCompleted: Boolean
)
