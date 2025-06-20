package com.racartech.app.rctandroidlts.functionbuttons

import android.app.Activity
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreDocument
//import com.racartech.library.rctandroidx.google.firestore.FirestoreField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KotlinFuncion1 {

    companion object {

        fun entry(activity: Activity, instance : FirebaseFirestore) {
            testing01(activity, instance)
        }


        fun testing01(activity: Activity, firestore: FirebaseFirestore){
            var collection : String  = "test_collection";
            var document : String = "test_document";
            var fieldName : String = "test_field_5";


            if (activity is androidx.lifecycle.LifecycleOwner) {
                activity.lifecycleScope.launch(Dispatchers.IO) {

                    //FirestoreField.setFieldAsString(firestore,collection,document,fieldName, "Hello World hehe")



                    // Update UI on Main thread
                    withContext(Dispatchers.Main) {
                        println("--------------------------------------------------------------")
                        //println("Field value: $fieldValue")
                        println("--------------------------------------------------------------")
                    }
                }
            } else {
                // Fallback or error log
                println("Activity does not implement LifecycleOwner. Cannot launch coroutine.")
            }
        }




    }
}
