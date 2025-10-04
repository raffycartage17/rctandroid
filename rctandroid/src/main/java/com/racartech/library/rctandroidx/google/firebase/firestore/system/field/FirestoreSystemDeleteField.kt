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


    public suspend fun deleteAndGetField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): HashMap<String, Any>? {
        return systemDeleteAndGetField(instance, collectionPath, documentPath, fieldName)
    }

    private suspend fun systemDeleteAndGetField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): HashMap<String, Any>? {
        return try {
            val docRef = instance.collection(collectionPath).document(documentPath)
            docRef.update(fieldName, FieldValue.delete()).await()

            val snapshot = docRef.get().await()
            if (snapshot.exists()) {
                snapshot.data?.let { HashMap(it) }
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }




    public suspend fun deleteField(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Boolean {
        return systemDeleteField(documentData, fieldName)
    }

    private suspend fun systemDeleteField(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Boolean {
        return if (documentData.containsKey(fieldName)) {
            documentData.remove(fieldName)
            true
        } else {
            false
        }
    }

}
