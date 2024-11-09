package com.racartech.app.rctandroidlts;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseSingleton {

    private static FirebaseSingleton instance;
    private final FirebaseFirestore firestore;
    private final FirebaseStorage storage;
    private final StorageReference storage_reference;

    // Private constructor to prevent instantiation
    private FirebaseSingleton() {
        firestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storage_reference = storage.getReference();
    }

    // Public method to get the Singleton instance
    public static FirebaseSingleton getInstance() {
        if (instance == null) {
            synchronized (FirebaseSingleton.class) {
                if (instance == null) {
                    instance = new FirebaseSingleton();
                }
            }
        }
        return instance;
    }

    // Getter for Firestore instance
    public FirebaseFirestore getFirestore() {
        return firestore;
    }

    // Getter for Storage instance
    public FirebaseStorage getStorage() {
        return storage;
    }

    // Getter for StorageReference instance
    public StorageReference getStorageReference() {
        return storage_reference;
    }
}

