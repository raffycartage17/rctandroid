package com.racartech.library.rctandroidx.google.firebase.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore



object FirestoreCollection {
    fun getCollectionReference(
        instance: FirebaseFirestore,
        collection: String
    ): CollectionReference {
        return instance.collection(collection)
    }

}