package com.lab.petguardian.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference

data class PetPlanResponse(
    val id: String? = null,
    val title: String? = null,
    val date: Timestamp? = null,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val petId: String? = null
)