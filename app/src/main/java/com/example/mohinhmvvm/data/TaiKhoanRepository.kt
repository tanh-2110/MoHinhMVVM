package com.example.mohinhmvvm.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class TaiKhoanRepository {
    private val auth = FirebaseAuth.getInstance()

    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    suspend fun login(email: String, password: String): FirebaseUser? {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        return result.user
    }

    suspend fun register(email: String, password: String): FirebaseUser? {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return result.user
    }

    fun logout() {
        auth.signOut()
    }
}
