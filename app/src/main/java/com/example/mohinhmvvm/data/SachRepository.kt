package com.example.mohinhmvvm.data
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SachRepository {
    private val db = FirebaseFirestore.getInstance()
    private val sachCollection = db.collection("sach")

    suspend fun getAll(): List<Sach> {
        val snapshot = sachCollection.get().await()
        return snapshot.documents.mapNotNull { it.toObject(Sach::class.java)?.copy(id = it.id) }
    }

    suspend fun insert(sach: Sach): String {
        val docRef = sachCollection.add(sach).await()
        return docRef.id
    }

    suspend fun update(sach: Sach) {
        if (sach.id.isNotEmpty()) {
            sachCollection.document(sach.id).set(sach).await()
        }
    }

    suspend fun delete(id: String) {
        sachCollection.document(id).delete().await()
    }
}
