package com.racartech.library.rctandroidx.google.firebase.firestore.system.field

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

object FirestoreSystemQuery {


    suspend fun executeQuery(
        query: Query
    ): HashMap<String, Map<String, Any?>> {
        val snapshot = query.get().await()
        val result = HashMap<String, Map<String, Any?>>()

        for (document in snapshot.documents) {
            document.data?.let { data ->
                result[document.id] = data
            }
        }

        return result
    }
}
