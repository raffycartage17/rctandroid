package com.racartech.app.rctandroidlts.functionbuttons

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.racartech.library.rctandroidx.database.offlinestore.OfflineStore
import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreCollection
import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreDocument

import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KotlinFuncion1 {

    companion object {

        fun entry(activity: Activity, context : Context, instance : FirebaseFirestore, offlineStore : OfflineStore) {
            testing01(activity, context,instance, offlineStore)
        }


        fun testing01(activity: Activity, context : Context, instance: FirebaseFirestore, offlineStore : OfflineStore){
            var documentPath : String = "test_collection/test_document"
            var odb = offlineStore
            odb.createDocument(documentPath)
            odb.putField(documentPath,"fullname", "Rafael Andaya Cartagena")
            odb.putField(documentPath,"age", 25)
            var fullname = odb.readField(documentPath, "fullname")
            var age = odb.readField(documentPath, "age")
            println("79308303 : Fullname : "+fullname)
            println("79308303 : Age : "+age)
        }




    }
}
