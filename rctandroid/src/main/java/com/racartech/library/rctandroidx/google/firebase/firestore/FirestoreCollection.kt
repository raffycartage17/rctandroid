package com.racartech.library.rctandroidx.google.firebase.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

object FirestoreCollection {

    /** Get collection reference */
    fun getCollectionReference(
        instance: FirebaseFirestore,
        collection: String
    ): CollectionReference {
        return instance.collection(collection)
    }

    /** Get all documents in a collection as a list of Maps */
    suspend fun getAllDocuments(
        instance: FirebaseFirestore,
        collection: String
    ): List<Map<String, Any>> {
        val snapshot = instance.collection(collection).get().await()
        return snapshot.documents.mapNotNull { it.data }
    }

    /** Add a new document with auto-generated ID */
    suspend fun addDocumentAutoId(
        instance: FirebaseFirestore,
        collection: String,
        data: Map<String, Any>
    ): String? {
        return try {
            val ref = instance.collection(collection).add(data).await()
            ref.id
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /** Create or set document with specific ID */
    suspend fun setDocumentWithId(
        instance: FirebaseFirestore,
        collection: String,
        documentId: String,
        data: Map<String, Any>
    ): Boolean {
        return try {
            instance.collection(collection).document(documentId).set(data).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /** Query documents with a where-equal-to condition */
    suspend fun queryEqualTo(
        instance: FirebaseFirestore,
        collection: String,
        field: String,
        value: Any
    ): List<Map<String, Any>> {
        val snapshot = instance.collection(collection)
            .whereEqualTo(field, value)
            .get().await()
        return snapshot.documents.mapNotNull { it.data }
    }

    /** Query documents ordered by a field (ascending or descending) */
    suspend fun queryOrdered(
        instance: FirebaseFirestore,
        collection: String,
        field: String,
        ascending: Boolean = true,
        limit: Long? = null
    ): List<Map<String, Any>> {
        var query: Query = instance.collection(collection).orderBy(
            field,
            if (ascending) Query.Direction.ASCENDING else Query.Direction.DESCENDING
        )
        if (limit != null) query = query.limit(limit)
        val snapshot = query.get().await()
        return snapshot.documents.mapNotNull { it.data }
    }

    /** Count documents in collection */
    suspend fun countDocuments(
        instance: FirebaseFirestore,
        collection: String
    ): Int {
        val snapshot = instance.collection(collection).get().await()
        return snapshot.size()
    }

    /** Delete all documents in a collection (use carefully!) */
    suspend fun deleteAllDocuments(
        instance: FirebaseFirestore,
        collection: String
    ): Boolean {
        return try {
            val snapshot = instance.collection(collection).get().await()
            val batch = instance.batch()
            for (doc in snapshot.documents) {
                batch.delete(doc.reference)
            }
            batch.commit().await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /** Listen to real-time changes in a collection */
    fun listenToCollection(
        instance: FirebaseFirestore,
        collection: String,
        onChanged: (List<Map<String, Any>>) -> Unit,
        onError: (Exception) -> Unit
    ): ListenerRegistration {
        return instance.collection(collection)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    onError(error)
                    return@addSnapshotListener
                }
                val data = snapshot?.documents?.mapNotNull { it.data } ?: emptyList()
                onChanged(data)
            }
    }

    /** Paginated query (start after lastDocumentId) */
    suspend fun getPaginatedDocuments(
        instance: FirebaseFirestore,
        collection: String,
        orderByField: String,
        limit: Long,
        lastDocumentId: String? = null
    ): List<Map<String, Any>> {
        var query: Query = instance.collection(collection)
            .orderBy(orderByField)
            .limit(limit)

        if (lastDocumentId != null) {
            val lastDocSnapshot = instance.collection(collection)
                .document(lastDocumentId)
                .get().await()
            query = query.startAfter(lastDocSnapshot)
        }

        val snapshot = query.get().await()
        return snapshot.documents.mapNotNull { it.data }
    }

    /** Check if collection is empty */
    suspend fun isCollectionEmpty(
        instance: FirebaseFirestore,
        collection: String
    ): Boolean {
        val snapshot = instance.collection(collection).limit(1).get().await()
        return snapshot.isEmpty
    }

    /** Get list of document IDs only */
    suspend fun getDocumentIds(
        instance: FirebaseFirestore,
        collection: String
    ): List<String> {
        val snapshot = instance.collection(collection).get().await()
        return snapshot.documents.map { it.id }
    }

}
