package com.racartech.library.rctandroid.google.firebase.firestore;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.racartech.library.rctandroid.json.RCTjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class RCTfirebaseFirestore {


    public static boolean doesFieldExist(HashMap<String, Object> document_data, String field_name, boolean ignore_case){
        ArrayList<String> keyset = getFields(document_data);
        boolean do_exist = false;
        if(ignore_case){
            for(int index = 0; index<keyset.size(); index++){
                if(keyset.get(index).equalsIgnoreCase(field_name)){
                    do_exist = true;
                    break;
                }
            }
        }else{
            for(int index = 0; index<keyset.size(); index++){
                if(keyset.get(index).equals(field_name)){
                    do_exist = true;
                    break;
                }
            }
        }
        return do_exist;
    }

    public static ArrayList<String> getFields(HashMap<String, Object> document_data){
        return new ArrayList<>(document_data.keySet());
    }

    public static String readField(HashMap<String, Object> document_data, String field_name, boolean field_name_ignore_case){
        boolean does_field_exist = doesFieldExist(document_data,field_name,field_name_ignore_case);
        if(does_field_exist){
            try {
                return Objects.requireNonNull(document_data.get(field_name)).toString();
            }catch(NullPointerException ignored){
                return null;
            }
        }else{
            return null;
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////



    public static FirebaseFirestore getInstance(){
        return FirebaseFirestore.getInstance();
    }

    public static boolean setDocumentData_WaitProgress(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            HashMap<String, Object> document_data,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicBoolean success_boolean = new AtomicBoolean(false);
        FirestoreUtil.createDocument_WaitProgress(
                instance,
                collection_path,
                document_path,
                document_data,
                finished_boolean,
                success_boolean);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return success_boolean.get();
    }

    public static boolean setDocumentData_WaitProgress(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicBoolean success_boolean = new AtomicBoolean(false);
        FirestoreUtil.createDocument_WaitProgress(
                instance,
                collection_path,
                document_path,
                finished_boolean,
                success_boolean);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return success_boolean.get();
    }



    public static boolean doesFieldExist(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String field_name,
            long thread_wait
            ){
        return ((RCTfirebaseFirestore.readField(
                collection_path,
                document_path,
                field_name,
                thread_wait,
                instance)) != null);
    }



    public static void createField(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String field_name,
            String field_value){
            FirestoreUtil.createField(instance,collection_path,document_path,field_name,field_value);
    }

    public static void createField_WaitProgress(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String field_name,
            String field_value,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        FirestoreUtil.createField_WaitProgress(instance,collection_path,document_path,field_name,field_value,finished_boolean);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
    }


    public static void deleteField_WaitProgress(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String field_name,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        FirestoreUtil.deleteField_WaitProgress(instance,collection_path,document_path,field_name,finished_boolean);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
    }

    public static void deleteField(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String field_name
    ){
        FirestoreUtil.deleteField(instance,collection_path,document_path,field_name);
    }

    public static void renameField(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String field_name,
            String new_field_name
    ){
        FirestoreUtil.renameField(
                instance,
                collection_path,
                document_path,
                field_name,
                new_field_name);
    }

    public static void renameField_WaitProgress(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String field_name,
            String new_field_name,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);

        FirestoreUtil.renameField_WaitProgress(
                instance,
                collection_path,
                document_path,
                field_name,
                new_field_name,
                finished_boolean);

        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
    }


    public static void deleteDocument_WaitProgress(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        FirestoreUtil.deleteDocument_WaitProgress(instance,collection_path,document_path,finished_boolean);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
    }

    public static void deleteDocument(
            FirebaseFirestore instance,
            String collection_path,
            String document_path){
        FirestoreUtil.deleteDocument(instance,collection_path,document_path);
    }



    public static void renameDocument(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String new_document_name){

        FirestoreUtil.renameDocument(
                instance,
                collection_path,
                document_path,
                new_document_name);
    }

    public static void renameDocument_WaitProgress(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String new_document_name,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);

        FirestoreUtil.renameDocument_WaitProgress(
                instance,
                collection_path,
                document_path,
                new_document_name,
                finished_boolean);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
    }




    public static void createDocument(
            FirebaseFirestore instance,
            String collection_path,
            String document_path){
            FirestoreUtil.createDocument(instance,collection_path,document_path);
    }

    public static void createDocument(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            HashMap<String, Object> document_data){
        FirestoreUtil.createDocument(
                instance,
                collection_path,
                document_path,
                document_data);
    }

    public static boolean createDocument_WaitProgress(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            long thread_wait){
            boolean return_boolean = false;
            AtomicBoolean finished_boolean = new AtomicBoolean(false);
            AtomicBoolean success_boolean = new AtomicBoolean(false);
            FirestoreUtil.createDocument_WaitProgress(
                    instance,
                    collection_path,
                    document_path,
                    finished_boolean,
                    success_boolean);
            while(!return_boolean){
                if(!finished_boolean.get()){
                    try {
                        Thread.sleep(thread_wait);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    return_boolean = true;
                }
            }
            return success_boolean.get();
    }

    public static boolean createDocument_WaitProgress(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            HashMap<String, Object> document_data,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicBoolean success_boolean = new AtomicBoolean(false);
        FirestoreUtil.createDocument_WaitProgress(
                instance,
                collection_path,
                document_path,
                document_data,
                finished_boolean,
                success_boolean);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return success_boolean.get();
    }


    public static String readField(FirebaseFirestore instance,
                                   String collection_path,
                                   String document_path,
                                   String field_name,
                                   long thread_wait){
        return readField(collection_path, document_path, field_name, thread_wait, instance);
    }


    public static String readField(
            String collection_path,
            String document_path,
            String field_name,
            long thread_wait,
            FirebaseFirestore instance){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<String> atomic_value = new AtomicReference<>(null);
        FirestoreUtil.readField(
                instance,
                collection_path,
                document_path,
                field_name,
                finished_boolean,
                atomic_value);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return atomic_value.get();
    }


    public static ArrayList<String> getFields(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            long thread_wait){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<ArrayList<String>> atomic_list = new AtomicReference<>(new ArrayList<>());
        FirestoreUtil.getFields(instance,collection_path,document_path,finished_boolean,atomic_list);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return atomic_list.get();
    }


    public static ArrayList<String> getDocumentList(
            FirebaseFirestore instance,
            String collection_path,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<ArrayList<String>> atomic_list = new AtomicReference<>(new ArrayList<>());
        FirestoreUtil.getDocumentList(instance,collection_path,finished_boolean,atomic_list);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return atomic_list.get();

    }



    public static HashMap<String, Object> readDocument(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            long thread_wait
    ){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<HashMap<String,Object>> atomic_list = new AtomicReference<>(new HashMap<>());
        FirestoreUtil.readDocument(instance,collection_path,document_path,finished_boolean,atomic_list);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return atomic_list.get();
    }

    public static HashMap<String, String> readDocument_WithJSONStringArrayValue(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            int json_array_index,
            String value_filter,
            long thread_wait
    ){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<HashMap<String,String>> atomic_list = new AtomicReference<>(new HashMap<>());
        FirestoreUtil.readDocument_WithJSONStringArrayValueFilter(
                instance,
                collection_path,
                document_path,
                json_array_index,
                value_filter,
                finished_boolean,
                atomic_list);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return atomic_list.get();
    }


    public static boolean doesDocumentExist(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicBoolean does_document_exist = new AtomicBoolean(false);
        FirestoreUtil.doesDocumentExist(instance,collection_path, document_path,finished_boolean,does_document_exist);
        while(!return_boolean){
            if(!finished_boolean.get()){
                try {
                    Thread.sleep(thread_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                return_boolean = true;
            }
        }
        return does_document_exist.get();
    }










    protected static class FirestoreUtil {

















        public static void doesDocumentExist(
                FirebaseFirestore fs_instance,
                String collectionPath,
                String documentPath,
                AtomicBoolean isFinished,
                AtomicBoolean documentExists) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    fs_instance.collection(collectionPath).document(documentPath).get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            documentExists.set(true);
                                        } else {
                                            documentExists.set(false);
                                        }
                                    } else {
                                        documentExists.set(false);
                                    }
                                    isFinished.set(true);
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    isFinished.set(true);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    isFinished.set(true);
                                }
                            });

                }
            }).start();
        }












        protected static void createDocument(FirebaseFirestore instance,String collection_path, String document_path) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Map<String, Object> data = new HashMap<>();
                    DocumentReference docRef = instance.collection(collection_path).document(document_path);
                    docRef.set(data, SetOptions.merge());
                }
            }).start();

        }


        protected static void createDocument(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                HashMap<String, Object> document_data) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    DocumentReference docRef = instance.collection(collection_path).document(document_path);
                    docRef.set(document_data, SetOptions.merge());
                }
            }).start();

        }

        protected static void createDocument_WaitProgress(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                AtomicBoolean finished_bool,
                AtomicBoolean success_bool
        ) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    Map<String, Object> data = new HashMap<>();
                    DocumentReference docRef = instance.collection(collection_path).document(document_path);
                    docRef.set(data, SetOptions.merge())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    finished_bool.set(true);
                                    success_bool.set(true);
                                }
                            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    finished_bool.set(true);
                                    success_bool.set(true);
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    finished_bool.set(true);
                                    success_bool.set(false);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    finished_bool.set(true);
                                    success_bool.set(false);
                                }
                            });

                }
            }).start();


        }

        protected static void createDocument_WaitProgress(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                HashMap<String, Object> document_data,
                AtomicBoolean finished_bool,
                AtomicBoolean success_bool
        ) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    DocumentReference docRef = instance.collection(collection_path).document(document_path);
                    docRef.set(document_data, SetOptions.merge())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    finished_bool.set(true);
                                    success_bool.set(true);
                                }
                            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    finished_bool.set(true);
                                    success_bool.set(true);
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    finished_bool.set(true);
                                    success_bool.set(false);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    finished_bool.set(true);
                                    success_bool.set(false);
                                }
                            });

                }
            }).start();


        }



        //To Test
        public static CollectionReference getCollectionReference(FirebaseFirestore instance,String collection_path) {
            return instance.collection(collection_path);
        }

        // Method to create a new Firestore document within a collection
        private static DocumentReference getDocumentReference(FirebaseFirestore instance,String collection_path, String document_path) {
            CollectionReference collection = getCollectionReference(instance,collection_path);
            return collection.document(document_path);
        }

        protected static void createField(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                String fieldName,
                Object fieldValue) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    DocumentReference document = getDocumentReference(instance,collection_path, document_path);
                    Map<String, Object> data = new HashMap<>();
                    data.put(fieldName, fieldValue);
                    document.update(data);

                }
            }).start();

        }

        protected static void createField_WaitProgress(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                String fieldName,
                Object fieldValue,
                AtomicBoolean finished_boolean) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    DocumentReference document = getDocumentReference(instance,collection_path, document_path);
                    Map<String, Object> data = new HashMap<>();
                    data.put(fieldName, fieldValue);
                    document.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            finished_boolean.set(true);
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finished_boolean.set(true);
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            finished_boolean.set(true);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            finished_boolean.set(true);
                        }
                    });

                }
            }).start();


        }



        protected static void deleteField_WaitProgress(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                String fieldName,
                AtomicBoolean finished_boolean) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    DocumentReference document = getDocumentReference(instance, collection_path, document_path);
                    Map<String, Object> data = new HashMap<>();
                    data.put(fieldName, FieldValue.delete()); // Use FieldValue.delete() to delete a field
                    document.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            finished_boolean.set(true);
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finished_boolean.set(true);
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            finished_boolean.set(true);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            finished_boolean.set(true);
                        }
                    });
                }
            }).start();
        }

        protected static void deleteField(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                String fieldName) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    DocumentReference document = getDocumentReference(instance, collection_path, document_path);
                    Map<String, Object> data = new HashMap<>();
                    data.put(fieldName, FieldValue.delete()); // Use FieldValue.delete() to delete a field
                    document.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
                }
            }).start();
        }





        public static void readField(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                String fieldName,
                AtomicBoolean finished_boolean,
                AtomicReference<String> atomic_value) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    Task<DocumentSnapshot> documentSnapshotTask = instance.collection(collection_path).document(document_path).get();
                    documentSnapshotTask.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                // Document exists, you can now access the specified field
                                if (documentSnapshot.contains(fieldName)) {
                                    try {
                                        String field_value = Objects.requireNonNull(documentSnapshot.get(fieldName)).toString();
                                        atomic_value.set(field_value);
                                    }catch(Exception ignored){}
                                    finished_boolean.set(true);
                                } else {
                                    finished_boolean.set(true);
                                }
                            } else {
                                finished_boolean.set(true);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            finished_boolean.set(true);
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            finished_boolean.set(true);
                        }
                    });

                }
            }).start();


        }




        public static void getFields(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                AtomicBoolean finished_boolean,
                AtomicReference<ArrayList<String>> atomic_list) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    instance.collection(collection_path).document(document_path).get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            try {
                                                ArrayList<String> inner_list = new ArrayList<>();
                                                inner_list.addAll(document.getData().keySet());
                                                atomic_list.get().clear();
                                                atomic_list.get().addAll(inner_list);
                                            }catch (Exception ex){
                                                ex.printStackTrace();
                                            }
                                            finished_boolean.set(true);
                                        } else {
                                            finished_boolean.set(true);
                                        }
                                    } else {
                                        finished_boolean.set(true);
                                    }
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    finished_boolean.set(true);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    finished_boolean.set(true);
                                }
                            });

                }
            }).start();


        }


        public static void getDocumentList(
                FirebaseFirestore instance,
                String collection_path,
                AtomicBoolean finished_boolean,
                AtomicReference<ArrayList<String>> atomic_list) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    CollectionReference collectionRef = instance.collection(collection_path);

                    collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                QuerySnapshot querySnapshot = task.getResult();
                                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                    List<DocumentSnapshot> documents = querySnapshot.getDocuments();
                                    ArrayList<String> inner_list = new ArrayList<>();
                                    for (DocumentSnapshot document : documents) {
                                        inner_list.add(document.getId());
                                    }
                                    atomic_list.get().clear();
                                    atomic_list.get().addAll(inner_list);
                                    finished_boolean.set(true);
                                } else {
                                    finished_boolean.set(true);
                                }
                            } else {
                                finished_boolean.set(true);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            finished_boolean.set(true);
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            finished_boolean.set(true);
                        }
                    });

                }
            }).start();


        }







        public static void readDocument(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                AtomicBoolean finished_boolean,
                AtomicReference<HashMap<String,Object>> atomic_list) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    instance.collection(collection_path).document(document_path).get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            try {
                                                HashMap<String,Object> data_map = (HashMap<String, Object>) document.getData();
                                                atomic_list.get().clear();
                                                if(data_map != null) {
                                                    atomic_list.get().putAll(data_map);
                                                }
                                            }catch (Exception ex){
                                                ex.printStackTrace();
                                            }
                                            finished_boolean.set(true);
                                        } else {
                                            finished_boolean.set(true);
                                        }
                                    } else {
                                        finished_boolean.set(true);
                                    }
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    finished_boolean.set(true);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    finished_boolean.set(true);
                                }
                            });

                }
            }).start();


        }



        public static void readDocument_WithJSONStringArrayValueFilter(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                int json_array_index,
                String value_filter,
                AtomicBoolean finished_boolean,
                AtomicReference<HashMap<String,String>> atomic_list) {


            new Thread(new Runnable() {
                @Override
                public void run() {

                    instance.collection(collection_path).document(document_path).get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            try {
                                                HashMap<String,Object> data_map = new HashMap<>();
                                                HashMap<String,String> filtered_map = new HashMap<>();
                                                for (Map.Entry<String, Object> entry : data_map.entrySet()) {
                                                    String key = entry.getKey();
                                                    String value = entry.getValue().toString();
                                                    ArrayList<String> value_array = RCTjson.convertStringToArrayList(value);
                                                    if(value_array.get(json_array_index).equals(value_filter)){
                                                        filtered_map.put(key,value);
                                                    }
                                                }
                                                atomic_list.get().clear();
                                                atomic_list.get().putAll(filtered_map);
                                            }catch (Exception ex){
                                                ex.printStackTrace();
                                            }
                                            finished_boolean.set(true);
                                        } else {
                                            finished_boolean.set(true);
                                        }
                                    } else {
                                        finished_boolean.set(true);
                                    }
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    finished_boolean.set(true);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    finished_boolean.set(true);
                                }
                            });

                }
            }).start();


        }




        protected static void deleteDocument_WaitProgress(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                AtomicBoolean finished_boolean) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    DocumentReference document = getDocumentReference(instance, collection_path, document_path);
                    document.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            finished_boolean.set(true);
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finished_boolean.set(true);
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            finished_boolean.set(true);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            finished_boolean.set(true);
                        }
                    });
                }
            }).start();
        }


        protected static void deleteDocument(
                FirebaseFirestore instance,
                String collection_path,
                String document_path) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    DocumentReference document = getDocumentReference(instance, collection_path, document_path);
                    document.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
                }
            }).start();
        }








        protected static void renameField_WaitProgress(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                String oldFieldName,
                String newFieldName,
                AtomicBoolean finished_boolean) {

            new Thread(new Runnable() {
                @Override
                public void run(){
                    DocumentReference document = getDocumentReference(instance, collection_path, document_path);
                    Map<String, Object> data = new HashMap<>();
                    // Remove the old field and add it with the new name
                    data.put(newFieldName, document.get().getResult().get(oldFieldName));
                    data.put(oldFieldName, FieldValue.delete());
                    document.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            finished_boolean.set(true);
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finished_boolean.set(true);
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            finished_boolean.set(true);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            finished_boolean.set(true);
                        }
                    });
                }
            }).start();
        }

        protected static void renameField(
                FirebaseFirestore instance,
                String collectionPath,
                String documentPath,
                String oldFieldName,
                String newFieldName) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    DocumentReference document = getDocumentReference(instance, collectionPath, documentPath);

                    // Read the current value of the field
                    document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Object originalValue = documentSnapshot.get(oldFieldName);

                            // Create a map to update the document
                            Map<String, Object> data = new HashMap<>();
                            data.put(newFieldName, originalValue); // Add the new field with the original value
                            data.put(oldFieldName, FieldValue.delete()); // Delete the old field

                            // Update the document
                            document.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    // Handle success if needed
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    // Handle completion if needed
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    // Handle cancellation if needed
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle failure if needed
                                }
                            });
                        }
                    });
                }
            }).start();
        }





        protected static void renameDocument(
                FirebaseFirestore instance,
                String collectionPath,
                String oldDocumentPath,
                String newDocumentName) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    DocumentReference oldDocumentRef = instance.document(oldDocumentPath);
                    DocumentReference newDocumentRef = instance.document(collectionPath + "/" + newDocumentName);

                    oldDocumentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Map<String, Object> data = documentSnapshot.getData();
                                if (data != null) {
                                    newDocumentRef.set(data)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    // New document created successfully with old data
                                                    oldDocumentRef.delete()
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    // Old document deleted successfully
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    // Handle failure to delete old document
                                                                }
                                                            });
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // Handle failure to create new document
                                                }
                                            });
                                }
                            } else {
                                // Handle the case where old document doesn't exist
                            }
                        }
                    });
                }
            }).start();
        }

        protected static void renameDocument_WaitProgress(
                FirebaseFirestore instance,
                String collection_path,
                String old_document_path,
                String new_document_name,
                AtomicBoolean finished_boolean) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    // Get reference to the old document
                    DocumentReference oldDocument = getDocumentReference(instance, collection_path, old_document_path);

                    // Read data from the old document
                    oldDocument.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                // Get the data from the old document
                                Map<String, Object> data = documentSnapshot.getData();

                                // Get reference to the new document with the new name
                                DocumentReference newDocument = getDocumentReference(instance, collection_path, new_document_name);

                                // Write the data to the new document
                                newDocument.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // New document created successfully, now delete the old document
                                        oldDocument.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                finished_boolean.set(true);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Handle failure to delete old document
                                                finished_boolean.set(true);
                                            }
                                        });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Handle failure to write to new document
                                        finished_boolean.set(true);
                                    }
                                });
                            } else {
                                // Handle case where old document doesn't exist
                                finished_boolean.set(true);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle failure to read old document
                            finished_boolean.set(true);
                        }
                    });
                }
            }).start();
        }





















    }










}



/*


    new Thread(new Runnable() {
            @Override
            public void run() {

            }
    }).start();




     */


