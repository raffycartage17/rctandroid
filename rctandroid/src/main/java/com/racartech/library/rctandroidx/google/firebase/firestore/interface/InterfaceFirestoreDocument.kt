package com.racartech.library.rctandroidx.google.firebase.firestore.`interface`

import com.google.firebase.firestore.FirebaseFirestore

internal interface InterfaceFirestoreDocument {


    // ----------------- readDocument Method -----------------

    suspend fun readDocument(instance : FirebaseFirestore, collectionPath : String, documentPath : String): HashMap<String, Any>?

    // ----------------- create, set, update Method -----------------
    suspend fun createMergeDocument(instance : FirebaseFirestore, collectionPath: String, documentPath: String, contents: HashMap<String, Any>): Boolean
    suspend fun createSetDocument(instance : FirebaseFirestore, collectionPath: String, documentPath: String, contents: HashMap<String, Any>): Boolean

    // ----------------- delete Method -----------------

    suspend fun deleteDocument(instance : FirebaseFirestore, collectionPath: String, documentPath : String) : Boolean

}