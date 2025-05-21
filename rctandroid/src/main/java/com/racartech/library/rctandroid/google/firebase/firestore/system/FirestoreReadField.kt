package com.racartech.library.rctandroid.google.firebase.firestore.system

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

internal object FirestoreReadField {

     public suspend inline fun <reified T> readField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): T? {
        return systemReadField<T>(instance, collectionPath, documentPath, fieldName)
    }


    private suspend inline fun <reified T> systemReadField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): T? {
        return try {
            val snapshot = instance
                .collection(collectionPath)
                .document(documentPath)
                .get()
                .await()

            if (snapshot.exists() && snapshot.contains(fieldName)) {
                snapshot.get(fieldName) as? T
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }






}