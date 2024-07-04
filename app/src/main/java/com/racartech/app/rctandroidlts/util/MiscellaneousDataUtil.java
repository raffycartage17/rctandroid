package com.racartech.app.rctandroidlts.util;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.racartech.library.rctandroid.google.firebase.storage.RCTfirebaseStorage;

public class MiscellaneousDataUtil{


    public static String getLanguageTrainedModel_English(FirebaseStorage fbs_instance){
        return RCTfirebaseStorage.getDownloadURL(
                fbs_instance,
                "/library_misc_data",
                "eng.traineddata",
                100
        );
    }





}
