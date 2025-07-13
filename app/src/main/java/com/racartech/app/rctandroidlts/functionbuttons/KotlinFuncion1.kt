package com.racartech.app.rctandroidlts.functionbuttons

import android.app.Activity
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreCollection
import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreDocument

import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KotlinFuncion1 {

    companion object {

        fun entry(activity: Activity, instance : FirebaseFirestore) {
            testing01(activity, instance)
        }


        fun testing01(activity: Activity, instance: FirebaseFirestore){
            var collection : String  = "test_collection";
            var document : String = "test_document";
            var fieldName : String = "test_field_5";


            if (activity is androidx.lifecycle.LifecycleOwner) {
                activity.lifecycleScope.launch(Dispatchers.IO) {


                    println("--------------------------------------------------------------")
                    var  reference = FirestoreCollection.getCollectionReference(instance,collection);
                    var query : Query = reference.whereArrayContains("sports","football").whereGreaterThanOrEqualTo("priority", 1).orderBy("country_name")
                    var result = FirestoreField.query(query);
                    for(documentData in result){
                        println("Document Name : "+documentData.key);
                        println("Document Data : "+documentData.value)
                        println("--------------------------------------------------------------")
                    }



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
