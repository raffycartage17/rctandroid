package com.racartech.app.rctandroidlts.functionbuttons

import android.app.Activity
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreCollection
import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreDocument

import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KotlinFuncion1 {

    companion object {

        fun entry(activity: Activity, instance : FirebaseFirestore) {
            testing01(activity, instance)
        }


        fun testing01(activity: Activity, instance: FirebaseFirestore) {

            FirestoreDocument.deleteDocument()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    FirestoreField.createUpdateFieldAsString(
                        instance,
                        "test_collection",
                        "test_document",
                        "test_field",
                        "test_value"
                    )

                    withContext(Dispatchers.Main) {
                        Log.d("KotlinFuncion1", "Firestore field updated successfully!")
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.e("KotlinFuncion1", "Error updating Firestore field", e)
                    }
                }
            }
        }





    }
}
