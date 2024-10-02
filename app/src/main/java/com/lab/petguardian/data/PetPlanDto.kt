package com.lab.petguardian.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.local.ReferenceSet

data class PetPlanDto(
    val petName: String,
    val title: String,
    val date: Timestamp,
    val description: String,
    val isCompleted: Boolean,
    val petId: String
)
