package com.lab.petguardian

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.lab.petguardian.data.PetDto
import com.lab.petguardian.data.PetResponse
import com.lab.petguardian.model.PetModel
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

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

    return try {
        PetDto(name, typePet, weight, neutered, gender, timeStamp)
    } catch (e: Exception) {
        null
    }
}

fun petResponseToDomain(petResponse: PetResponse): PetModel? {
    if (petResponse.name == null || petResponse.dateOfBirth == null || petResponse.type == null || petResponse.id == null || petResponse.weight == null || petResponse.gender == null || petResponse.neutered == null) return null
    val dateOfBirth = timeStampToString(petResponse.dateOfBirth) ?: return null
    return PetModel(
        name = petResponse.name,
        dateOfBirth = dateOfBirth,
        type = petResponse.type,
        id = petResponse.id,
        weight = petResponse.weight,
        neutered = petResponse.neutered,
        gender = petResponse.gender
    )
}

fun timeStampToString(timestamp: Timestamp?): String? {
    timestamp ?: return null
    return try {
        val date = timestamp.toDate()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        sdf.format(date)
    } catch (e: Exception) {
        null
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun petAge(dateOfBirth: String): String? {
    if (dateOfBirth.isNotEmpty()) {
        val dateOfBirth1 = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val currentDate = LocalDate.now()
        return ChronoUnit.YEARS.between(dateOfBirth1, currentDate).toString()
    } else {
        return "0"
    }
}

