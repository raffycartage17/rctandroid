package com.racartech.app.rctandroidlts.functionbuttons

import com.google.firebase.firestore.FirebaseFirestore
import com.racartech.library.rctandroidx.google.firestore.FirestoreField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KotlinFuncion1 {

    companion object{

        fun entry() {
            // Launch a coroutine
            GlobalScope.launch(Dispatchers.IO) {
                // Call your suspend function here
                val fieldValue = FirestoreField.readFieldAsString(
                    FirebaseFirestore.getInstance(),
                    "test_collection",
                    "test_document",
                    "test_field_2"
                    );

                // Switch back to Main thread to update UI or return result
                withContext(Dispatchers.Main) {
                    println("--------------------------------------------------------------")
                    println("Field value: $fieldValue")
                    println("--------------------------------------------------------------")
                }
            }
        }



    }



}