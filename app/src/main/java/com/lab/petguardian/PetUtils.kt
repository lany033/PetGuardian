package com.lab.petguardian

import android.util.Log
import com.google.firebase.Timestamp
import com.lab.petguardian.data.PetDto
import java.lang.Exception

fun prepareDTO(
    name: String,
    type: Boolean,
    dateOfBirth: Long?,
    weight: Double,
    neutered: String,
    gender: String
): PetDto? {
    if (name.isBlank()) return null
    val timeStamp = if (dateOfBirth != null) {
        val seconds = dateOfBirth / 1000
        val nanoseconds = ((dateOfBirth % 1000) * 1000000).toInt()
        Timestamp(seconds, nanoseconds)
    } else {
        Timestamp.now()
    }

    val typePet = if (type) {
        "Cat"
    } else {
        "Dog"
    }

    //val weightDto = weight ?: 0.0

    return try {
        PetDto(name, typePet, weight, neutered, gender, timeStamp)
    } catch (e: Exception) {
        null
    }
}

