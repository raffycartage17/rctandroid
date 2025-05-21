package com.racartech.library.rctandroid.google.firebase.firestore.system

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

internal object FirestoreCreateUpdateField {

    public suspend inline fun <reified T> createUpdateField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: T
    ) {
        systemCreateField(instance, collectionPath, documentPath, fieldName, value)
    }

    private suspend inline fun <reified T> systemCreateField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: T
    ) {
        try {
            val docRef = instance.collection(collectionPath).document(documentPath)
            val snapshot = docRef.get().await()

            // If document doesn't exist, create it
            if (!snapshot.exists()) {
                docRef.set(mapOf(fieldName to value)).await()
            } else {
                docRef.update(fieldName, value).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
