package com.racartech.library.rctandroidx.google.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.racartech.library.rctandroidx.google.firebase.firestore.`interface`.InterfaceFirestoreDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirebaseSystemCreateSetDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirebaseSystemDeleteDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirestoreSystemCreateMergeDocument
import com.racartech.library.rctandroidx.google.firebase.firestore.system.document.FirestoreSystemReadDocument

object FirestoreDocument : InterfaceFirestoreDocument{


    override suspend fun readDocument(instance: FirebaseFirestore, collectionPath: String, documentPath: String): HashMap<String, Any>? {
        return FirestoreSystemReadDocument.readDocument(instance, collectionPath, documentPath);
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
        return FirebaseSystemCreateSetDocument.createOrSetDocument(instance,  collectionPath, documentPath, contents);
    }

    override suspend fun deleteDocument(
        instance: FirebaseFirestore,
        collectionPath: String,
        documentPath: String
    ): Boolean {
        return FirebaseSystemDeleteDocument.deleteDocument(instance, collectionPath, documentPath)
    }

}