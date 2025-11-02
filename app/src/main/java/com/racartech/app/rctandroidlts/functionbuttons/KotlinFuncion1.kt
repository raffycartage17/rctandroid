package com.racartech.app.rctandroidlts.functionbuttons

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.racartech.library.rctandroidx.cloudflare.RCTcloudflareR2
import com.racartech.library.rctandroidx.file.RCTfileX
import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreCollection
import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreDocument

import com.racartech.library.rctandroidx.google.firebase.firestore.FirestoreField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class KotlinFuncion1 {

    companion object {


        private var r2AccessKey = "ba30898daefe72301cda163b77c1d568"
        private var r2SecretKey = "52f2bad6d36e3f61ceedaf0167027d50e578a3f041f3bc521f0c773abfbe0082"
        private var r2BucketName = "racalogy-main"
        private var r2Endpoint = "https://70354c5068dbd65a085dccb69648487f.r2.cloudflarestorage.com"

        fun entry(activity: Activity,context: Context, instance : FirebaseFirestore) {
            testing01(activity, context, instance)
        }


        fun testing01(activity: Activity, context: Context, instance: FirebaseFirestore) {
            CoroutineScope(Dispatchers.IO).launch {
//                var file = File(RCTfileX.getDirIntAppFiles(context) + "/test_document_from_android.txt")
//                var contents : ArrayList<String> = arrayListOf()
//                contents.add("Rafael Cartagena")
//                contents.add("Colleen Ardieta")
//                RCTfileX.createFile(file)
//                RCTfileX.writeFile(file,contents)
//
//
//
             var r2Instance = RCTcloudflareR2.CloudflareR2Instance(r2AccessKey, r2SecretKey, r2Endpoint, r2BucketName);
//                RCTcloudflareR2.uploadFile(r2Instance,"test_from_android.txt",file)


                var downloadFile = File(RCTfileX.getDirIntAppFiles(context)+"/downloaded_file.txt")
                RCTcloudflareR2.downloadFile(r2Instance,"test_from_android.txt",downloadFile)




            }
        }





    }
}
