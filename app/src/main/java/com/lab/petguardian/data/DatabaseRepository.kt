package com.lab.petguardian.data

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.lab.petguardian.model.PetModel
import com.lab.petguardian.petResponseToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
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
            db.collection("users").document(userId!!).collection("pets").snapshots().map { qs ->
                qs.toObjects(PetResponse::class.java).mapNotNull { petResponse ->
                    petResponseToDomain(petResponse)
                }
            }
        }

    }

    fun getPetById(id: String): Flow<PetModel?> {
        return flow {
            val petDocument = db.collection("users")
                .document(userId!!)
                .collection("pets")
                .document(id)
                .get()
                .await()

            if (petDocument.exists()) {
                val pet = petDocument.toObject(PetResponse::class.java)
                emit(pet?.let { petResponseToDomain(it) })
            } else {
                emit(null) // Devuelve null si no existe el documento
            }
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
            db.collection("users").document(userIdDocument).collection("pets").document(customId)
                .set(model).await()
        }
        Log.d("Save", userIdDocument)
    }

    private fun getCustomId(): String {
        return Date().time.toString()
    }

    suspend fun addPlanByPet(petPlanDto: PetPlanDto) {
        val userIdDocument = userId.toString()
        val customId = getCustomId()
        val documentReference= db.collection("users").document(userIdDocument).collection("plans").document(customId)
        val model = hashMapOf(
            "id" to customId,
            "name" to petPlanDto.namePet,
            "date" to petPlanDto.date,
            "description" to petPlanDto.description,
            "isCompleted" to petPlanDto.isCompleted,
            "petPlanReference" to documentReference
        )
        if (userId != null) {
            documentReference.set(model).await()
        }
        Log.d("Save", userIdDocument)
    }

}