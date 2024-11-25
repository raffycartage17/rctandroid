package com.racartech.library.rctandroid.google.firebase.firestore;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class DocumentManager{

    private final String DOCUMENT;
    private final DocumentReference DOCUMENT_REFERENCE;
    private final HashMap<String, Object> DATA;
    private long THREAD_WAIT;
    private boolean LISTEN_FOR_SERVER_UPDATE;

    public DocumentManager(
            FirebaseFirestore instance,
            String collection_path,
            String document_name,
            boolean listen_for_server_updates,
            long thread_wait
    ){
        String document_path = collection_path.concat("/").concat(document_name);
        if(RCTfirebaseFirestore.isDocumentPath(document_path)) {
            this.THREAD_WAIT = thread_wait;
            this.DOCUMENT = document_path;
            this.DOCUMENT_REFERENCE = instance.collection(collection_path).document(this.DOCUMENT);
            this.DATA = RCTfirebaseFirestore.readDocument(this.DOCUMENT_REFERENCE,this.THREAD_WAIT);
            this.LISTEN_FOR_SERVER_UPDATE = listen_for_server_updates;
        }else{
            throw new NullPointerException("Path is not a document path");
        }
    }


    public HashMap<String, Object> updateDocumentOnServer(){
        RCTfirebaseFirestore.createDocument_WaitProgress(
                this.DOCUMENT_REFERENCE,
                this.DATA,
                this.THREAD_WAIT
        );
        return this.DATA;
    }




    //Reset the document data base on what is currently on the server
    public HashMap<String, Object> rollback(){
        this.DATA.clear();
        this.DATA.putAll(RCTfirebaseFirestore.readDocument(this.DOCUMENT_REFERENCE,this.THREAD_WAIT));
        return this.DATA;
    }



    public void setListenToServerUpdates(boolean listenToServerUpdates){
        //TODO
    }




    public Object createField(String key, Object value){
        return this.DATA.put(key, value);
    }
    public Object readField(String key){
        return this.DATA.get(key);
    }
    public Object setField(String key, Object value){
        return this.DATA.put(key, value);
    }
    public Object deleteField(String key, Object value){
        return this.DATA.put(key, value);
    }


    public String readField_asString(String key){
        return (String) this.DATA.get(key);
    }

    public int readField_asInteger(String key, int default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof Integer) {
            return (Integer) return_value; // Safe unboxing
        }

        // Return default if null or not an Integer
        return default_value;
    }

    public double readField_asDouble(String key, double default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof Double) {
            return (Double) return_value; // Safe unboxing
        }

        // Return default if null or not a Double
        return default_value;
    }

    public float readField_asFloat(String key, float default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof Float) {
            return (Float) return_value; // Safe unboxing
        }

        // Return default if null or not a Float
        return default_value;
    }

    public long readField_asLong(String key, long default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof Long) {
            return (Long) return_value; // Safe unboxing
        }

        // Return default if null or not a Long
        return default_value;
    }


    public short readField_asShort(String key, short default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof Short) {
            return (Short) return_value; // Safe unboxing
        }

        // Return default if null or not a Short
        return default_value;
    }

    public byte readField_asByte(String key, byte default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof Byte) {
            return (Byte) return_value; // Safe unboxing
        }

        // Return default if null or not a Byte
        return default_value;
    }

    public boolean readField_asBoolean(String key, boolean default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof Boolean) {
            return (Boolean) return_value; // Safe unboxing
        }

        // Return default if null or not a Boolean
        return default_value;
    }

    public char readField_asChar(String key, char default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof Character) {
            return (Character) return_value; // Safe unboxing
        }

        // Return default if null or not a Character
        return default_value;
    }

    public String[] readField_asStringArray(String key, String[] default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof String[]) {
            return (String[]) return_value; // Safe cast
        }

        // Return default if null or not a String[]
        return default_value;
    }

    public int[] readField_asIntArray(String key, int[] default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof int[]) {
            return (int[]) return_value; // Safe cast
        }

        // Return default if null or not an int[]
        return default_value;
    }

    public double[] readField_asDoubleArray(String key, double[] default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof double[]) {
            return (double[]) return_value; // Safe cast
        }

        // Return default if null or not a double[]
        return default_value;
    }

    public float[] readField_asFloatArray(String key, float[] default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof float[]) {
            return (float[]) return_value; // Safe cast
        }

        // Return default if null or not a float[]
        return default_value;
    }

    public long[] readField_asLongArray(String key, long[] default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof long[]) {
            return (long[]) return_value; // Safe cast
        }

        // Return default if null or not a long[]
        return default_value;
    }

    public short[] readField_asShortArray(String key, short[] default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof short[]) {
            return (short[]) return_value; // Safe cast
        }

        // Return default if null or not a short[]
        return default_value;
    }

    public byte[] readField_asByteArray(String key, byte[] default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof byte[]) {
            return (byte[]) return_value; // Safe cast
        }

        // Return default if null or not a byte[]
        return default_value;
    }

    public char[] readField_asCharArray(String key, char[] default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof char[]) {
            return (char[]) return_value; // Safe cast
        }

        // Return default if null or not a char[]
        return default_value;
    }

    public boolean[] readField_asBooleanArray(String key, boolean[] default_value) {
        Object return_value = this.DATA.get(key);

        if (return_value instanceof boolean[]) {
            return (boolean[]) return_value; // Safe cast
        }

        // Return default if null or not a boolean[]
        return default_value;
    }





    protected class DocumentManagerUtil{

    }


}



