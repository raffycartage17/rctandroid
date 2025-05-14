package com.racartech.library.rctandroidx.google.firebase.firestore.system.document

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

internal object FirebaseSystemDeleteDocument {

    suspend fun deleteDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String
    ): Boolean {
        return systemDeleteDocument(instance, collectionPath, documentPath)
    }

    // Private function that performs the actual deletion
    private suspend fun systemDeleteDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String
    ): Boolean {
        return try {
            // Perform the delete operation and await the result
            instance.collection(collectionPath)
                .document(documentPath)
                .delete()
                .await()

            // Return true if deletion is successful
            true
        } catch (e: Exception) {
            // Catch any exceptions and print the stack trace
            e.printStackTrace()

            // Return false if an error occurred
            false
        }
    }

}