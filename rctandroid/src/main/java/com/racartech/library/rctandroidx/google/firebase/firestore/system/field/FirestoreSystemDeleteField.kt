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



    public suspend fun deleteFieldAndGetDocumentData(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): HashMap<String, Any>? {

        return systemDeleteFieldSystemAndGetDocumentData(instance, collectionPath, documentPath, fieldName)
    }

    private suspend fun systemDeleteFieldSystemAndGetDocumentData(

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




    public suspend fun deleteFieldGetValue(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Any? {
        return systemDeleteFieldGetValue(documentData, fieldName)
    }

    private suspend fun systemDeleteFieldGetValue(
        documentData: HashMap<String, Any>,
        fieldName: String
    ): Any? {
        if (documentData.containsKey(fieldName)) {
            return documentData.remove(fieldName)
        } else {
            return null
        }
    }


    public suspend fun deleteFieldGetValue(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Any? {
        return systemDeleteFieldGetValue(instance, collectionPath, documentPath, fieldName)
    }

    private suspend fun systemDeleteFieldGetValue(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Any? {
        return try {
            val docRef = instance.collection(collectionPath).document(documentPath)

            // Get the current document snapshot
            val snapshot = docRef.get().await()

            // If document exists and contains the field
            if (snapshot.exists() && snapshot.contains(fieldName)) {
                val valueToRemove = snapshot.get(fieldName)

                // Delete the field from Firestore
                docRef.update(fieldName, FieldValue.delete()).await()

                valueToRemove
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }



}
