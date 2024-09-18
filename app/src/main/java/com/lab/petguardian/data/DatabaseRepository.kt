package com.lab.petguardian.data

import android.content.Context
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.lab.petguardian.model.PetModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val db: FirebaseFirestore,
    authManager: AuthManager
) {
    private var userId = authManager.getCurrentUser()?.uid

    fun getPets(): Flow<List<PetModel>> {

        return if (userId == null) {
            flowOf(emptyList())
        } else {
            db.collection(userId!!).snapshots().map { qs ->
                qs.toObjects(PetResponse::class.java).mapNotNull { petResponse ->
                    petResponseToDomain(petResponse)
                }
            }
        }

    }

    private fun petResponseToDomain(petResponse: PetResponse): PetModel? {
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

    private fun timeStampToString(timestamp: Timestamp?): String? {
        timestamp ?: return null
        return try {
            val date = timestamp.toDate()
            val sdf = SimpleDateFormat("EEEE dd MMMM", Locale.getDefault())
            sdf.format(date)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addPet(pet: PetDto) {
        val userIdDocument = userId.toString()
        val customId = getCustomId()
        val model = hashMapOf(
            "id" to customId,
            "name" to pet.name,
            "dateOfBirth" to pet.dateOfBirth,
            "type" to pet.type,
            "weight" to pet.weight,
            "neutered" to pet.neutered,
            "gender" to pet.gender
        )
        if (userId != null) {
            db.collection("users").document(userIdDocument).collection("pets").document(customId).set(model).await()
        }
        Log.d("Save", userIdDocument)
    }

    private fun getCustomId(): String {
        return Date().time.toString()
    }

}