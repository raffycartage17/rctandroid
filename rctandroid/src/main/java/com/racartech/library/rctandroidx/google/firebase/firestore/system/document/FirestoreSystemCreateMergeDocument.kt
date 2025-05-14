package com.racartech.library.rctandroidx.google.firebase.firestore.system.document

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

internal object FirestoreSystemCreateMergeDocument {



    suspend fun createMergeDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        data: Map<String, Any>
    ): Boolean {
        return systemCreateMergeDocument(instance, collectionPath, documentPath, data)
    }

    private suspend fun systemCreateMergeDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        data: Map<String, Any>
    ): Boolean {
        return try {
            instance
                .collection(collectionPath)
                .document(documentPath)
                .set(data, SetOptions.merge())
                .await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


}