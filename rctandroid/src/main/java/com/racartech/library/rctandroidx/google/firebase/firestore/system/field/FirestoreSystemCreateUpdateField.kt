package com.racartech.library.rctandroidx.google.firebase.firestore.system.field

import com.google.firebase.firestore.FirebaseFirestore
import com.racartech.library.rctandroidx.google.firebase.firestore.model.FieldData
import kotlinx.coroutines.tasks.await

internal object FirestoreSystemCreateUpdateField {

    public suspend inline fun <reified T> createUpdateField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: T
    ) {
        systemCreateField(instance, collectionPath, documentPath, fieldName, value)
    }

    public suspend fun createUpdateFields(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fields: List<FieldData>
    ) {
        systemCreateFields(instance, collectionPath, documentPath, fields)
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


    private suspend fun systemCreateFields(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fields: List<FieldData>
    ) {
        try {
            val docRef = instance.collection(collectionPath).document(documentPath)
            val snapshot = docRef.get().await()

            val dataMap = fields.associate { it.name to it.value }

            if (!snapshot.exists()) {
                docRef.set(dataMap).await()
            } else {
                docRef.update(dataMap).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    public suspend inline fun <reified T> createUpdateField(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: T
    ) {
        systemCreateUpdateField(documentData, fieldName, value)
    }

    private suspend inline fun <reified T> systemCreateUpdateField(
        documentData: HashMap<String, Any>,
        fieldName: String,
        value: T
    ) {
        try {
            documentData[fieldName] = value as Any
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    public suspend fun createUpdateFields(
        documentData: HashMap<String, Any>,
        fields: List<FieldData>
    ) {
        systemCreateFields(documentData, fields)
    }

    private suspend fun systemCreateFields(
        documentData: HashMap<String, Any>,
        fields: List<FieldData>
    ) {
        try {
            for (field in fields) {
                documentData[field.name] = field.value
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}
