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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;
import com.racartech.library.rctandroid.json.RCTgoogleGSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class RCTfirebaseFirestore {



    public static HashMap<String, Object> readDocument(
            FirebaseFirestore fs_instance,
            String document_path,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        return readDocument(fs_instance, collection_path, document_name, thread_wait);
    }

    public static HashMap<String, Object> readDocument(
            FirebaseFirestore fs_instance,
            Source source,
            String document_path,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        return readDocument(fs_instance,source, collection_path, document_name, thread_wait);
    }

    public static void createDocument(
            FirebaseFirestore fs_instance,
            String document_path
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        createDocument(fs_instance, collection_path, document_name);
    }

    public static void createDocument_WaitProgress(
            FirebaseFirestore fs_instance,
            String document_path,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        createDocument_WaitProgress(fs_instance, collection_path, document_name, thread_wait);
    }



    public static String readField(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);

        return readField(
                fs_instance,
                collection_path,
                document_name,
                field_name,
                thread_wait
        );
    }

    public static Object readField_asObject(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        return readField_asObject(fs_instance, collection_path, document_name,thread_wait);
    }



    public static void setField(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            String field_value
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        createField(fs_instance, collection_path, document_name, field_name, field_value);
    }

    public static void setField(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            Object field_value
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        createField(fs_instance, collection_path, document_name, field_name, field_value);
    }

    public static void createField(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            String field_value
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        createField(fs_instance, collection_path, document_name, field_name, field_value);
    }

    public static void createField(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            Object field_value
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        createField(fs_instance, collection_path, document_name, field_name, field_value);

    }




    public static void setField_WaitProgress(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            String field_value,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);

        createField_WaitProgress(
                fs_instance,
                collection_path,
                document_name,
                field_name,
                field_value,
                thread_wait
        );
    }

    public static void createField_WaitProgress(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            String field_value,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);

        createField_WaitProgress(
                fs_instance,
                collection_path,
                document_name,
                field_name,
                field_value,
                thread_wait
        );
    }

    public static void createField_WaitProgress(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            Object field_value,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);

        createField_WaitProgress(
                fs_instance,
                collection_path,
                document_name,
                field_name,
                field_value,
                thread_wait
        );
    }








    public static void setField(
            FirebaseFirestore instance,
            String collection,
            String document,
            String field_name,
            String field_value
            ){
        createField(
                instance,
                collection,
                document,
                field_name,
                field_value
        );
    }

    public static void setField(
            FirebaseFirestore instance,
            String collection,
            String document,
            String field_name,
            Object field_value
    ){
        createField(
                instance,
                collection,
                document,
                field_name,
                field_value
        );
    }

    public static void setField_WaitProgress(
            FirebaseFirestore instance,
            String collection,
            String document,
            String field_name,
            String field_value,
            long thread_wait
    ){
        createField_WaitProgress(
                instance,
                collection,
                document,
                field_name,
                field_value,
                thread_wait
        );
    }

    public static void setField_WaitProgress(
            FirebaseFirestore instance,
            String collection,
            String document,
            String field_name,
            Object field_value,
            long thread_wait
    ){
        createField_WaitProgress(
                instance,
                collection,
                document,
                field_name,
                field_value,
                thread_wait
        );
    }


    public static void setDocument(
            FirebaseFirestore instance,
            String collection,
            String document,
            HashMap<String, Object> document_data
    ){
        createDocument(
                instance,
                collection,
                document,
                document_data
        );
    }

    public static void setDocument_WaitProgress(
            FirebaseFirestore instance,
            String collection,
            String document,
            HashMap<String, Object> document_data,
            long thread_wait
    ){
        createDocument_WaitProgress(
                instance,
                collection,
                document,
                document_data,
                thread_wait
        );

    }


    public static long getDocumentSize(
            FirebaseFirestore fs_instance,
            String document_path,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        return getDocumentSize(fs_instance,collection_path, document_name, thread_wait);
    }

    public static void renameDocument(
            FirebaseFirestore fs_instance,
            String document_path,
            String new_document_name
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        renameDocument(fs_instance,collection_path, document_name,new_document_name);
    }

    public static void renameDocument_WaitProgress(
            FirebaseFirestore fs_instance,
            String document_path,
            String new_document_name,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);

        renameDocument_WaitProgress(
                fs_instance,
                collection_path,
                document_name,
                new_document_name,
                thread_wait
        );

    }

    public static void deleteDocument(
            FirebaseFirestore fs_instance,
            String document_path
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        deleteDocument(fs_instance,collection_path, document_name);
    }

    public static void deleteDocument_WaitProgress(
            FirebaseFirestore fs_instance,
            String document_path,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        deleteDocument_WaitProgress(fs_instance,collection_path, document_name, thread_wait);
    }

    public static HashMap<String, String> readDocument_WithJSONStringArrayValue(
            FirebaseFirestore fs_instance,
            String document_path,
            int json_array_index,
            String value_filter,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);

        return readDocument_WithJSONStringArrayValue(
                fs_instance,
                collection_path,
                document_name,
                json_array_index,
                value_filter,
                thread_wait
        );
    }

    public static void createDocument(
            FirebaseFirestore fs_instance,
            String document_path,
            HashMap<String, Object> document_data
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        createDocument(fs_instance, collection_path, document_name, document_data);
    }

    public static void createDocument_WaitProgress(
            FirebaseFirestore fs_instance,
            String document_path,
            HashMap<String, Object> document_data,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        createDocument_WaitProgress(
                fs_instance,
                collection_path,
                document_name,
                document_data,
                thread_wait
        );
    }

    public static void deleteField(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        deleteField(fs_instance,collection_path, document_name, field_name);
    }

    public static void deleteField_WaitProgress(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);
        deleteField_WaitProgress(fs_instance,collection_path, document_name, field_name, thread_wait);


    }


    public static void renameField(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            String new_field_name
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);

        renameField(fs_instance, collection_path, document_name,field_name,new_field_name);
    }

    public static void renameField_WaitProgress(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            String new_field_name,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);

        renameField_WaitProgress(fs_instance, collection_path, document_name,field_name,new_field_name, thread_wait);

    }

    public static void setField_WaitProgress(
            FirebaseFirestore fs_instance,
            String document_path,
            String field_name,
            Object field_value,
            long thread_wait
    ){
        String collection_path = getCollectionPath(document_path);
        String document_name = getDocumentNameFromPath(document_path);

        createField_WaitProgress(
                fs_instance,
                collection_path,
                document_name,
                field_name,
                field_value,
                thread_wait
        );
    }





















    public static HashMap<String, HashMap<String, Object>> query(
            ChainedQuery query,
            long thread_wait
    ){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<HashMap<String, HashMap<String, Object>>> atomic_value = new AtomicReference<>(new HashMap<>());
        FirestoreUtil.query(
                query,
                atomic_value,
                finished_boolean
        );
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



    public static long getDocumentSize(
            HashMap<String, Object> document_data
    ) {

        if (document_data == null) {
            return 0;
        }

        // Calculate the size of the document
        long documentSize = 0;

        for (Map.Entry<String, Object> entry : document_data.entrySet()) {
            try {
                String key = entry.getKey();
                Object value = entry.getValue();

                // Key size: 1 byte per character + 1 byte overhead
                documentSize += key.length() + 1;

                // Value size based on type
                if (value instanceof String) {
                    // Strings: 1 byte per character + 1 byte overhead
                    documentSize += ((String) value).length() + 1;
                } else if (value instanceof Long || value instanceof Double) {
                    // Numbers: 8 bytes + 1 byte overhead
                    documentSize += 8 + 1;
                } else if (value instanceof Boolean) {
                    // Booleans: 1 byte + 1 byte overhead
                    documentSize += 1 + 1;
                } else if (value instanceof Map) {
                    // Map (nested document): Recursive call
                    documentSize += getMapSize((Map<String, Object>) value);
                } else if (value instanceof List) {
                    // Array: Recursive call for each element
                    documentSize += getListSize((List<Object>) value);
                } else if (value instanceof byte[]) {
                    // Binary: size of array + 1 byte overhead
                    documentSize += ((byte[]) value).length + 1;
                }

            }catch (Exception ignored){}
        }

        return documentSize;
    }


    public static long getDocumentSize(
            FirebaseFirestore fs_instance,
            String collection,
            String document,
            long thread_wait
    ) {
        // Read the document data using the provided method
        HashMap<String, Object> documentData = RCTfirebaseFirestore.readDocument(fs_instance, collection, document, thread_wait);

        if (documentData == null) {
            return 0;
        }

        // Calculate the size of the document
        long documentSize = 0;

        for (Map.Entry<String, Object> entry : documentData.entrySet()) {
            try {
                String key = entry.getKey();
                Object value = entry.getValue();

                // Key size: 1 byte per character + 1 byte overhead
                documentSize += key.length() + 1;

                // Value size based on type
                if (value instanceof String) {
                    // Strings: 1 byte per character + 1 byte overhead
                    documentSize += ((String) value).length() + 1;
                } else if (value instanceof Long || value instanceof Double) {
                    // Numbers: 8 bytes + 1 byte overhead
                    documentSize += 8 + 1;
                } else if (value instanceof Boolean) {
                    // Booleans: 1 byte + 1 byte overhead
                    documentSize += 1 + 1;
                } else if (value instanceof Map) {
                    // Map (nested document): Recursive call
                    documentSize += getMapSize((Map<String, Object>) value);
                } else if (value instanceof List) {
                    // Array: Recursive call for each element
                    documentSize += getListSize((List<Object>) value);
                } else if (value instanceof byte[]) {
                    // Binary: size of array + 1 byte overhead
                    documentSize += ((byte[]) value).length + 1;
                }

            }catch (Exception ignored){}
        }

        return documentSize;
    }

    private static long getMapSize(Map<String, Object> map) {
        long size = 0;

        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                // Key size: 1 byte per character + 1 byte overhead
                size += key.length() + 1;

                // Value size based on type
                if (value instanceof String) {
                    size += ((String) value).length() + 1;
                } else if (value instanceof Long || value instanceof Double) {
                    size += 8 + 1;
                } else if (value instanceof Boolean) {
                    size += 1 + 1;
                } else if (value instanceof Map) {
                    size += getMapSize((Map<String, Object>) value);
                } else if (value instanceof List) {
                    size += getListSize((List<Object>) value);
                } else if (value instanceof byte[]) {
                    size += ((byte[]) value).length + 1;
                }
            }
        }catch (Exception ignored){}
        return size;
    }

    private static long getListSize(List<Object> list) {
        long size = 0;
        for (Object value : list) {
            // Value size based on type
            if (value instanceof String) {
                size += ((String) value).length() + 1;
            } else if (value instanceof Long || value instanceof Double) {
                size += 8 + 1;
            } else if (value instanceof Boolean) {
                size += 1 + 1;
            } else if (value instanceof Map) {
                size += getMapSize((Map<String, Object>) value);
            } else if (value instanceof List) {
                size += getListSize((List<Object>) value);
            } else if (value instanceof byte[]) {
                size += ((byte[]) value).length + 1;
            }
        }
        return size;
    }


    public static void createField(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String field_name,
            Object field_value){
        FirestoreUtil.createField(instance,collection_path,document_path,field_name,field_value);
    }

    public static void createField_WaitProgress(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String field_name,
            Object field_value,
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
            long thread_wait
    ){
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
            DocumentReference documentReference,
            HashMap<String, Object> document_data,
            long thread_wait){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicBoolean success_boolean = new AtomicBoolean(false);
        FirestoreUtil.createDocument_WaitProgress(
                documentReference,
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



    public static Object readField_asObject(
            FirebaseFirestore instance,
            String collection_path,
            String document_path,
            String field_name,
            long thread_wait){

        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<Object> atomic_value = new AtomicReference<>(null);
        FirestoreUtil.readField_asObject(
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
            DocumentReference documentReference,
            long thread_wait
    ){
        return readDocument(documentReference, Source.DEFAULT, thread_wait);
    }

    public static HashMap<String, Object> readDocument(
            DocumentReference documentReference,
            Source source,
            long thread_wait
    ){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<HashMap<String,Object>> atomic_list = new AtomicReference<>(new HashMap<>());
        FirestoreUtil.readDocument(documentReference, source,finished_boolean,atomic_list);
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
        return readDocument(instance,Source.DEFAULT,collection_path,document_path,thread_wait);
    }



    public static HashMap<String, Object> readDocument(
            FirebaseFirestore instance,
            Source source,
            String collection_path,
            String document_path,
            long thread_wait
    ){
        boolean return_boolean = false;
        AtomicBoolean finished_boolean = new AtomicBoolean(false);
        AtomicReference<HashMap<String,Object>> atomic_list = new AtomicReference<>(new HashMap<>());
        FirestoreUtil.readDocument(instance,collection_path,document_path,finished_boolean,atomic_list, source);
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




        protected static void createDocument_WaitProgress(
                DocumentReference documentReference,
                HashMap<String, Object> document_data,
                AtomicBoolean finished_bool,
                AtomicBoolean success_bool
        ) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    documentReference.set(document_data, SetOptions.merge())
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


        public static void readField_asObject(
                FirebaseFirestore instance,
                String collection_path,
                String document_path,
                String fieldName,
                AtomicBoolean finished_boolean,
                AtomicReference<Object> atomic_value) {

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
                                        Object field_value = Objects.requireNonNull(documentSnapshot.get(fieldName));
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
                AtomicReference<HashMap<String,Object>> atomic_list,
                Source source
        ) {

            if(collection_path == null){
                collection_path = "";
            }

            String finalCollection_path = collection_path;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    instance.collection(finalCollection_path).document(document_path).get()
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


        public static void readDocument(
                DocumentReference documentReference,
                Source source,
                AtomicBoolean finished_boolean,
                AtomicReference<HashMap<String,Object>> atomic_list) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    documentReference.get(source).addOnCompleteListener(task -> {
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
                                                    ArrayList<String> value_array = RCTgoogleGSON.convertStringToArrayList(value);
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




        protected static void query(
                ChainedQuery query,
                AtomicReference<HashMap<String, HashMap<String, Object>>> atomic_value,
                AtomicBoolean finished_boolean
        ) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    
                    query.getChainedQuery().get()
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        try {
                                            atomic_value.get().put(
                                                    document.getReference().getPath(),
                                                    new HashMap<>(document.getData())
                                            );
                                        }catch (Exception ex){
                                            ex.printStackTrace();
                                        }
                                    }
                                    finished_boolean.set(true);
                                }else{
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












































































































































































    }




    // Method to get the whole collection path from a document path
    public static String getCollectionPath(String path) {
        String[] pathSegments = path.split("/");
        // If the path has at least two segments, return all but the last segment
        if (pathSegments.length >= 2) {
            StringBuilder collectionPath = new StringBuilder();
            for (int i = 0; i < pathSegments.length - 1; i++) {
                collectionPath.append(pathSegments[i]);
                if (i < pathSegments.length - 2) {
                    collectionPath.append("/"); // Append slash between segments
                }
            }
            return collectionPath.toString();
        }
        return null; // Invalid path format
    }

    // Method to check if a path refers to a document
    public static boolean isDocumentPath(String path) {
        String[] pathSegments = path.split("/");
        return pathSegments.length % 2 == 1; // Document path has an odd number of segments
    }

    // Method to check if a path refers to a collection
    public static boolean isCollectionPath(String path) {
        String[] pathSegments = path.split("/");
        return pathSegments.length % 2 == 0; // Collection path has an even number of segments
    }

    // Method to get the collection name from a path
    public static String getCollectionNameFromPath(String path) {
        String[] pathSegments = path.split("/");
        if (pathSegments.length % 2 == 1) {
            return pathSegments[pathSegments.length - 2]; // Collection is before the document
        } else if (pathSegments.length > 1) {
            return pathSegments[pathSegments.length - 1]; // Last segment might be a collection
        }
        return null; // Invalid path format
    }


    public static String getDocumentNameFromPath(String path) {
        String[] pathSegments = path.split("/");
        if (pathSegments.length > 1 && pathSegments.length % 2 != 0) {
            return pathSegments[pathSegments.length - 1]; // Last segment is the document
        }
        return null; // Invalid path format
    }








}





