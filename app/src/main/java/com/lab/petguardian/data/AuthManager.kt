package com.lab.petguardian.data

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume


sealed class AuthRes<out T> {
    data class Success<T>(val data: T) : AuthRes<T>()
    data class Error(val errorMessage: String) : AuthRes<Nothing>()
}

class AuthManager @Inject constructor(
    private val auth: FirebaseAuth
) {
    fun getCurrentUser() = auth.currentUser

    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUser? {
        return suspendCancellableCoroutine { cancellableContinuation ->
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val user = it.user
                cancellableContinuation.resume(user)
            }.addOnFailureListener {
                AuthRes.Error(it.message ?: "Error al crear usuario")
            }
        }
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser? {
        return suspendCancellableCoroutine { cancellableContinuation ->
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                val user = it.user
                cancellableContinuation.resume(user)
            }.addOnFailureListener {
                //Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                AuthRes.Error( it.message ?: "Error al iniciar sesión")
            }
        }
    }

    suspend fun resetPassword(email: String): AuthRes<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthRes.Success(Unit)
        } catch (e: Exception) {
            AuthRes.Error(e.message ?: "Error al restablecer contraseña")
        }
    }

    fun signOut(signInClient: SignInClient) {
        auth.signOut()
        signInClient.signOut()
    }


  suspend fun signInWithGoogleCredential(credential: AuthCredential): AuthRes<FirebaseUser>? {
        return try {
            val firebaseUser = auth.signInWithCredential(credential).await()
            firebaseUser.user?.let {
                AuthRes.Success(it)
            } ?: throw Exception("Sign in with Google failed.")
        } catch (e: Exception) {
            AuthRes.Error(e.message ?: "Sign in with Google failed.")
        }
    }

}