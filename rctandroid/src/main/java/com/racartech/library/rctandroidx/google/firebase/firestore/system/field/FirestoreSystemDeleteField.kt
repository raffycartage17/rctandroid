package com.racartech.library.rctandroidx.google.firebase.firestore.system.field

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

internal object FirestoreSystemDeleteField {

    public suspend fun deleteField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        systemDeleteField(instance, collectionPath, documentPath, fieldName)
    }

    private suspend fun systemDeleteField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ) {
        try {
            val docRef = instance.collection(collectionPath).document(documentPath)
            docRef.update(fieldName, FieldValue.delete()).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
