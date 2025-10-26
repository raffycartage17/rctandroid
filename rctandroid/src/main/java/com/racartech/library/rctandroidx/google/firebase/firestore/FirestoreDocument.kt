package com.racartech.library.rctandroidx.google.firebase.firestore

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import com.racartech.library.rctandroidx.google.firebase.firestore.`interface`.InterfaceFirestoreDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirebaseSystemCreateSetDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirebaseSystemDeleteDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirestoreSystemCreateMergeDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirestoreSystemReadDocument

object FirestoreDocument : InterfaceFirestoreDocument {

    //region --- Core interface implementation ---
    override suspend fun readDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String
    ): HashMap<String, Any>? {
        return FirestoreSystemReadDocument.readDocument(instance, collectionPath, documentPath)
    }

    override suspend fun createMergeDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        contents: HashMap<String, Any>
    ): Boolean {
        return FirestoreSystemCreateMergeDocument.createMergeDocument(instance, collectionPath, documentPath, contents)
    }

    override suspend fun createSetDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        contents: HashMap<String, Any>
    ): Boolean {
        return FirebaseSystemCreateSetDocument.createOrSetDocument(instance, collectionPath, documentPath, contents)
    }

    override suspend fun deleteDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String
    ): Boolean {
        return FirebaseSystemDeleteDocument.deleteDocument(instance, collectionPath, documentPath)
    }
    //endregion


    //region --- Additional Utility Functions ---

    /** Check if a document exists */
    suspend fun documentExists(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String
    ): Boolean {
        val snapshot = instance.collection(collectionPath).document(documentPath).get().await()
        return snapshot.exists()
    }

    /** Update specific fields in a document */
    suspend fun updateFields(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fields: Map<String, Any>
    ): Boolean {
        return try {
            instance.collection(collectionPath).document(documentPath).update(fields).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /** Increment a numeric field atomically */
    suspend fun incrementField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        incrementBy: Number
    ): Boolean {
        return try {
            instance.collection(collectionPath).document(documentPath)
                .update(fieldName, FieldValue.increment(incrementBy.toDouble()))
                .await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /** Delete a specific field */
    suspend fun deleteField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String
    ): Boolean {
        return try {
            instance.collection(collectionPath).document(documentPath)
                .update(fieldName, FieldValue.delete()).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /** Get a specific field value safely (returns default if missing) */
    suspend fun <T> getFieldValueOrDefault(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        defaultValue: T
    ): T {
        val snapshot = instance.collection(collectionPath).document(documentPath).get().await()
        return snapshot.get(fieldName) as? T ?: defaultValue
    }

    /** Listen to document changes in real-time */
    fun listenToDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        onChanged: (Map<String, Any>?) -> Unit,
        onError: (Exception) -> Unit
    ): ListenerRegistration {
        return instance.collection(collectionPath).document(documentPath)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    onError(error)
                } else {
                    onChanged(snapshot?.data)
                }
            }
    }

    /** Batch write operation */
    suspend fun batchWrite(
        instance: FirebaseFirestore,
        operations: suspend (FirebaseFirestore) -> Unit
    ) {
        val batch = instance.batch()
        operations(instance) // user can use instance.collection().document().set() inside
        batch.commit().await()
    }

    /** Duplicate document to another location */
    suspend fun duplicateDocument(
        instance: FirebaseFirestore,
        sourceCollection: String,
        sourceDoc: String,
        destCollection: String,
        destDoc: String
    ): Boolean {
        return try {
            val data = readDocument(instance, sourceCollection, sourceDoc)
            if (data != null) {
                createSetDocument(instance, destCollection, destDoc, data)
            } else false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /** Replace all fields (overwrite) safely */
    /** Replace all fields (overwrite) safely */
    suspend fun replaceDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        newData: Map<String, Any>
    ): Boolean {
        return try {
            instance.collection(collectionPath)
                .document(documentPath)
                .set(newData) // this overwrites the document entirely
                .await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


    /** Append an element to an array field */
    suspend fun appendToArrayField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Any
    ): Boolean {
        return try {
            instance.collection(collectionPath).document(documentPath)
                .update(fieldName, FieldValue.arrayUnion(value)).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /** Remove an element from an array field */
    suspend fun removeFromArrayField(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String,
        fieldName: String,
        value: Any
    ): Boolean {
        return try {
            instance.collection(collectionPath).document(documentPath)
                .update(fieldName, FieldValue.arrayRemove(value)).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}
