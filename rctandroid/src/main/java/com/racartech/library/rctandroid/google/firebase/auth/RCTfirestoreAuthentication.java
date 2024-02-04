package com.racartech.library.rctandroid.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RCTfirestoreAuthentication {



    public static String getCurrentUserEmail(FirebaseAuth firebase_auth){
        return firebase_auth.getCurrentUser().getEmail();
    }

    public static FirebaseUser getCurrentUser(FirebaseAuth firebase_auth){
        return firebase_auth.getCurrentUser();
    }

}
