package com.racartech.library.rctandroidx.google.firebase.firestore.system.document

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirestoreSystemReadDocument {


    suspend fun readDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String
    ): HashMap<String, Any>? {
        return systemReadDocument(instance, collectionPath, documentPath)
    }

    private suspend fun systemReadDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String
    ): HashMap<String, Any>? {
        return try {
            val snapshot = instance
                .collection(collectionPath)
                .document(documentPath)
                .get()
                .await()

            if (snapshot.exists()) {
                snapshot.data as? HashMap<String, Any>
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }





}