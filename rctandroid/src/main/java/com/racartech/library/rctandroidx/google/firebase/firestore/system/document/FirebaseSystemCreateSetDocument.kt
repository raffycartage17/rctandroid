package com.racartech.library.rctandroidx.google.firebase.firestore.system.document

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

internal object FirebaseSystemCreateSetDocument {

    // Public method to create or set a Firestore document
    suspend fun createOrSetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        data: Map<String, Any>
    ): Boolean {
        return systemCreateOrSetDocument(instance, collectionPath, documentPath, data)
    }

    // Private internal function to handle Firestore create or set logic
    private suspend fun systemCreateOrSetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        data: Map<String, Any>
    ): Boolean {
        return try {
            instance
                .collection(collectionPath)
                .document(documentPath)
                .set(data) // No SetOptions.merge() to replace the entire document
                .await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}