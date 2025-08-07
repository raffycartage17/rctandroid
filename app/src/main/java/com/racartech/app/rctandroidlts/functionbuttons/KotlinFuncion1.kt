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

        fun entry(
            activity: Activity,
            context : Context,
            instance : FirebaseFirestore,
            offlineStore : OfflineStore,
            documentPath : String,
            fieldName : String,
            fieldValue : String
        ) {
            testing01(activity, context,instance, offlineStore, documentPath, fieldName,fieldValue)
        }


        fun testing01(activity: Activity, context : Context, instance: FirebaseFirestore, offlineStore : OfflineStore, documentPath : String, fieldName : String, fieldValue : String){
            var odb = offlineStore
            odb.createDocument(documentPath)
            odb.putField(documentPath,fieldName, fieldValue)
            var fieldValue = odb.readField(documentPath, fieldName)
            println("79308303 : Field Age : "+fieldName)
            println("79308303 : Field Value : "+fieldValue)
        }




    }
}
