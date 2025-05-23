package com.racartech.library.rctandroid.google.firebase.firestore.system

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

internal object FirestoreSetField {

    public suspend inline fun <reified T> setField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: T
    ) {
        systemSetField(instance, collectionPath, documentPath, fieldName, value)
    }

    private suspend inline fun <reified T> systemSetField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: T
    ) {
        try {
            val docRef = instance.collection(collectionPath).document(documentPath)

            // Using SetOptions.merge() to preserve existing fields and only update the specified field
            docRef.set(mapOf(fieldName to value), SetOptions.merge()).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
