package com.racartech.library.rctandroid.google.firebase.storage;

import java.util.ArrayList;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class RCTfirebaseStorage {


    public final static String NULL_RESULT_IDENTIFIER = "NULL";
    private static AtomicReference<String> X0001_DOWNLOAD_URL = new AtomicReference<>(null);
    private static AtomicReference<String> X0002_DOWNLOAD_URL = new AtomicReference<>(null);
    private static AtomicReference<String> X0003_DOWNLOAD_URL = new AtomicReference<>(null);
    private static AtomicReference<String> X0004_DOWNLOAD_URL = new AtomicReference<>(null);
    private static AtomicReference<String> X0005_DOWNLOAD_URL = new AtomicReference<>(null);
    private static AtomicReference<String> X0006_DOWNLOAD_URL = new AtomicReference<>(null);


    /**
     * <h2>Description</h2>
     * Create a file in the root directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker class
     * @author Rafael Andaya Cartagena
     * @return String - download url of the created file
     * @param thread_sleep_ms length of time that the thread will wait to query the generated url in millisecond.
     */
    public static String createOverrideFile_GetURL(String file_name, ArrayList<String> file_contents, long thread_sleep_ms){
        RCTfirebaseStorageUtil.X0001_DOWNLOAD_URL.set(null);
        boolean download_url_created = false;
        RCTfirebaseStorageUtil.createWrite_File_X0001(file_name,file_contents);
        String generated_download_url = null;
        while(!download_url_created){
            String queried_download_url = RCTfirebaseStorageUtil.X0001_DOWNLOAD_URL.get();
            if(queried_download_url != null){
                generated_download_url = queried_download_url;
                download_url_created = true;
            }else{
                try {
                    Thread.sleep(thread_sleep_ms);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }
        RCTfirebaseStorageUtil.X0001_DOWNLOAD_URL.set(null);
        if(generated_download_url.equals(NULL_RESULT_IDENTIFIER)){
            return null;
        }else{
            return generated_download_url;
        }
    }

    /**
     * <h2>Description</h2>
     * Create a file in the root directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker class
     * @author Rafael Andaya Cartagena
     */
    public static void createOverrideFile(String file_name, ArrayList<String> file_contents){
        RCTfirebaseStorageUtil.createWrite_File(file_name,file_contents);
    }

    /**
     * <h2>Description</h2>
     * Create a file in the desired directory of your Firebase Storage
     *
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Sample Directory : "test/a", this will create the file in "storage root/test/a" directory
     * <br>
     * Always call this method on a worker class
     * @author Rafael Andaya Cartagena
     * @return String - download url of the created file
     * @param thread_sleep_ms length of time that the thread will wait to query the generated url in millisecond.
     */
    public static String createOverrideFile_GetURL(String directory,String file_name, ArrayList<String> file_contents, long thread_sleep_ms){
        RCTfirebaseStorageUtil.X0002_DOWNLOAD_URL.set(null);
        boolean download_url_created = false;
        RCTfirebaseStorageUtil.createWrite_File_X0002(directory,file_name,file_contents);
        String generated_download_url = null;
        while(!download_url_created){
            String queried_download_url = RCTfirebaseStorageUtil.X0002_DOWNLOAD_URL.get();
            if(queried_download_url != null){
                generated_download_url = queried_download_url;
                download_url_created = true;
            }else{
                try {
                    Thread.sleep(thread_sleep_ms);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }
        RCTfirebaseStorageUtil.X0002_DOWNLOAD_URL.set(null);
        if(generated_download_url.equals(NULL_RESULT_IDENTIFIER)){
            return null;
        }else{
            return generated_download_url;
        }
    }

    /**
     * <h2>Description</h2>
     * Create a file in the desired directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Sample Directory : "test/a", this will create the file in "storage root/test/a" directory
     * <br>
     * Always call this method on a worker class
     * @author Rafael Andaya Cartagena
     */
    public static void createOverrideFile(String directory, String file_name, ArrayList<String> file_contents){
        RCTfirebaseStorageUtil.createWrite_File(directory,file_name,file_contents);
    }


    /**
     * <h2>Description</h2>
     * Create a file in the root directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker class
     * @author Rafael Andaya Cartagena
     * @return String - download url of the created file
     * @param thread_sleep_ms length of time that the thread will wait to query the generated url in millisecond.
     */
    public static String createEmptyFile_GetURL(String file_name, long thread_sleep_ms){
        RCTfirebaseStorageUtil.X0005_DOWNLOAD_URL.set(null);
        boolean download_url_created = false;
        RCTfirebaseStorageUtil.createEmptyFile_X0005(file_name);
        String generated_download_url = null;
        while(!download_url_created){
            String queried_download_url = RCTfirebaseStorageUtil.X0005_DOWNLOAD_URL.get();
            if(queried_download_url != null){
                generated_download_url = queried_download_url;
                download_url_created = true;
            }else{
                try {
                    Thread.sleep(thread_sleep_ms);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }
        RCTfirebaseStorageUtil.X0005_DOWNLOAD_URL.set(null);
        if(generated_download_url.equals(NULL_RESULT_IDENTIFIER)){
            return null;
        }else{
            return generated_download_url;
        }
    }

    /**
     * <h2>Description</h2>
     * Create a file in the root directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     */
    public static void createEmptyFile(String file_name){
        RCTfirebaseStorageUtil.createEmptyFile(file_name);
    }

    /**
     * <h2>Description</h2>
     * Create a file in the root directory of your Firebase Storage
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     */
    public static String createEmptyFile_GetURL(String directory, String file_name, long thread_sleep_ms){
        RCTfirebaseStorageUtil.X0004_DOWNLOAD_URL.set(null);
        boolean download_url_created = false;
        RCTfirebaseStorageUtil.createEmptyFile_X0004(directory,file_name);
        String generated_download_url = null;
        while(!download_url_created){
            String queried_download_url = RCTfirebaseStorageUtil.X0004_DOWNLOAD_URL.get();
            if(queried_download_url != null){
                generated_download_url = queried_download_url;
                download_url_created = true;
            }else{
                try {
                    Thread.sleep(thread_sleep_ms);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }
        RCTfirebaseStorageUtil.X0004_DOWNLOAD_URL.set(null);
        if(generated_download_url.equals(NULL_RESULT_IDENTIFIER)){
            return null;
        }else{
            return generated_download_url;
        }
    }

    /**
     * <h2>Description</h2>
     * Create a file in the specified directory relative to the root
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     */
    public static void createEmptyFile(String directory, String file_name){
        RCTfirebaseStorageUtil.createEmptyFile(directory,file_name);
    }

    /**
     * <h2>Description</h2>
     * Create the files in the specified directory relative to the root
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     */
    public static void createEmptyFiles(String directory, ArrayList<String> file_names){
        RCTfirebaseStorageUtil.createEmptyFiles(directory,file_names);
    }


    public static void createDirectory(String directory){
        RCTfirebaseStorageUtil.createDirectory(directory);
    }

    /**
     * <h2>Description</h2>
     * <br>
     * Sample Directory : "test/a", this code will look for the file that is inside "storage_root/test/a"
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     * @return String - the download url of the target file
     */
    public static String getDownloadURL(String directory, String file_name, long thread_sleep_ms){
        RCTfirebaseStorageUtil.X0003_DOWNLOAD_URL.set(null);
        boolean download_url_created = false;
        RCTfirebaseStorageUtil.getDownloadURL_X0003(directory,file_name);
        String generated_download_url = null;
        while(!download_url_created){
            String queried_download_url = RCTfirebaseStorageUtil.X0003_DOWNLOAD_URL.get();
            if(queried_download_url != null){
                generated_download_url = queried_download_url;
                download_url_created = true;
            }else{
                try {
                    Thread.sleep(thread_sleep_ms);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }
        RCTfirebaseStorageUtil.X0003_DOWNLOAD_URL.set(null);
        if(generated_download_url.equals(NULL_RESULT_IDENTIFIER)){
            return null;
        }else{
            return generated_download_url;
        }
    }

    /**
     * <h2>Description</h2>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     * @return String - the download url of the target file
     */
    public static String getDownloadURL(String file_name, long thread_sleep_ms){
        RCTfirebaseStorageUtil.X0006_DOWNLOAD_URL.set(null);
        boolean download_url_created = false;
        RCTfirebaseStorageUtil.getDownloadURL_X0006(file_name);
        String generated_download_url = null;
        while(!download_url_created){
            String queried_download_url = RCTfirebaseStorageUtil.X0006_DOWNLOAD_URL.get();
            if(queried_download_url != null){
                generated_download_url = queried_download_url;
                download_url_created = true;
            }else{
                try {
                    Thread.sleep(thread_sleep_ms);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }
        RCTfirebaseStorageUtil.X0006_DOWNLOAD_URL.set(null);
        if(generated_download_url.equals(NULL_RESULT_IDENTIFIER)){
            return null;
        }else{
            return generated_download_url;
        }
    }


    /**
     * <h2>Description</h2>
     * Create the files in the specified directory relative to the root, this method will wait for the progress before stopping.
     * This class wont delete and recreate the file it already existed but instead override it.
     * <br>
     * Always call this method on a worker thread
     * @author Rafael Andaya Cartagena
     */
    public static boolean createEmptyFiles_WaitProgress(String directory_path, ArrayList<String> file_names, long thread_wait){
        RCTfirebaseStorageUtil.I_0001_WAIT_INTEGER.set(-1);

        final ArrayList<String> cloned_file_names = new ArrayList<>(file_names);

        RCTfirebaseStorageUtil.createEmptyFiles_WaitProgress(directory_path,cloned_file_names);

        try {
            boolean finished_boolean = false;
            while (!finished_boolean) {
                if(RCTfirebaseStorageUtil.I_0001_WAIT_INTEGER.get() != (cloned_file_names.size() - 1)){
                    try {
                        Thread.sleep(thread_wait);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    finished_boolean = true;
                }

            }

            RCTfirebaseStorageUtil.I_0001_WAIT_INTEGER.set(-1);
            return finished_boolean;
        }catch(Exception exx){
            exx.printStackTrace();
            return false;
        }
    }





    public static void deleteFile(String filename){
        RCTfirebaseStorageUtil.deleteFile(filename);
    }

    public static void deleteFile(ArrayList<String> filenames){
        RCTfirebaseStorageUtil.deleteFile(filenames);
    }

    public static void deleteFile(String directory, String filename){
        RCTfirebaseStorageUtil.deleteFile(directory,filename);
    }

    public static void deleteFile(String directory, ArrayList<String> filenames){
        RCTfirebaseStorageUtil.deleteFile(directory,filenames);
    }

    public static void clearDirectory_ImmediateFile(String directory){
        RCTfirebaseStorageUtil.clearDirectory_ImmediateFiles(directory);
    }




    public static boolean deleteFile_WaitProgress(String filename, long thread_wait){
        //Always call this method on a worker thread
        try {
            AtomicBoolean is_finished = new AtomicBoolean(false);
            RCTfirebaseStorageUtil.deleteFile_WaitProgress(is_finished, filename);
            while (!is_finished.get()) {
                Thread.sleep(thread_wait);
            }
            return is_finished.get();
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile_WaitProgress(ArrayList<String> filenames, long thread_wait){
        //Always call this method on a worker thread
        try {
            AtomicBoolean is_finished = new AtomicBoolean(false);
            RCTfirebaseStorageUtil.deleteFile_WaitProgress(is_finished, filenames);
            while (!is_finished.get()) {
                Thread.sleep(thread_wait);
            }
            return is_finished.get();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile_WaitProgress(String directory, String filename, long thread_wait){
        //Always call this method on a worker thread
        try {
            AtomicBoolean is_finished = new AtomicBoolean(false);
            RCTfirebaseStorageUtil.deleteFile_WaitProgress(is_finished, directory, filename);
            while (!is_finished.get()) {
                Thread.sleep(thread_wait);
            }
            return is_finished.get();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile_WaitProgress(String directory, ArrayList<String> filenames, long thread_wait){
        //Always call this method on a worker thread
        try {
            AtomicBoolean is_finished = new AtomicBoolean(false);
            RCTfirebaseStorageUtil.deleteFile_WaitProgress(is_finished, directory, filenames);
            while (!is_finished.get()) {
                Thread.sleep(thread_wait);
            }
            return is_finished.get();
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean clearDirectory_ImmediateFile_WaitProgress(String directory, long thread_wait){
        //Always call this method on a worker thread
        try {
            AtomicBoolean is_finished = new AtomicBoolean(false);
            boolean finish_boolean = false;
            RCTfirebaseStorageUtil.clearDirectory_ImmediateFiles_WaitProgress(is_finished, directory);
            while (!finish_boolean) {
                if(!is_finished.get()){
                    Thread.sleep(thread_wait);
                }else{
                    finish_boolean = true;
                }
            }
            return is_finished.get();
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }























    ////////////////////////Utilities//////////////////////




}


class RCTfirebaseStorageUtil{

    private final static String NULL_RESULT_IDENTIFIER = "NULL";

    protected static AtomicReference<String> X0001_DOWNLOAD_URL = new AtomicReference<>(null);
    protected static AtomicReference<String> X0002_DOWNLOAD_URL = new AtomicReference<>(null);
    protected static AtomicReference<String> X0003_DOWNLOAD_URL = new AtomicReference<>(null);
    protected static AtomicReference<String> X0004_DOWNLOAD_URL = new AtomicReference<>(null);
    protected static AtomicReference<String> X0005_DOWNLOAD_URL = new AtomicReference<>(null);
    protected static AtomicReference<String> X0006_DOWNLOAD_URL = new AtomicReference<>(null);

    protected static AtomicInteger I_0001_WAIT_INTEGER = new AtomicInteger(-1);

    public static void createWrite_File_X0001(String fileName, ArrayList<String> file_contents) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Concatenate the ArrayList into a single string with newline characters
        StringBuilder fileContentBuilder = new StringBuilder();
        for (int i = 0; i < file_contents.size(); i++) {
            String line = file_contents.get(i);
            fileContentBuilder.append(line).append("\n");
        }
        String fileContent = fileContentBuilder.toString().trim(); // Remove the last newline character

        // Create a reference to the file you want to create
        StorageReference fileRef = storageRef.child(fileName);

        byte[] data = fileContent.getBytes();

        fileRef.putBytes(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // File upload successful
                        // Get the download URL of the uploaded file
                        Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                        downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    // Get the URL of the uploaded file
                                    Uri downloadUrl = task.getResult();
                                    String downloadUrlString = downloadUrl.toString();
                                    X0001_DOWNLOAD_URL.set(downloadUrlString);
                                } else {
                                    X0001_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                                    // Handle the error
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                X0001_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                X0001_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                        X0001_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        X0001_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                });
    }


    public static void createWrite_File(String fileName, ArrayList<String> file_contents) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Concatenate the ArrayList into a single string with newline characters
        StringBuilder fileContentBuilder = new StringBuilder();
        for (int i = 0; i < file_contents.size(); i++) {
            String line = file_contents.get(i);
            fileContentBuilder.append(line).append("\n");
        }
        String fileContent = fileContentBuilder.toString().trim(); // Remove the last newline character

        // Create a reference to the file you want to create
        StorageReference fileRef = storageRef.child(fileName);

        byte[] data = fileContent.getBytes();

        fileRef.putBytes(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // File upload successful
                        // Get the download URL of the uploaded file
                        Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                        downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                    }
                });
    }


    public static void createWrite_File_X0002(String folder_path, String file_name, ArrayList<String> file_contents) {
        AtomicReference<String> download_url_string = new AtomicReference<>(null);
        //This code wont recreate the file if it already existed
        String[] folder_path_branches = folder_path.split("/");

        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Concatenate the ArrayList into a single string with newline characters
        StringBuilder fileContentBuilder = new StringBuilder();
        for (int i = 0; i < file_contents.size(); i++) {
            String line = file_contents.get(i);
            fileContentBuilder.append(line).append("\n");
        }
        String fileContent = fileContentBuilder.toString().trim(); // Remove the last newline character

        // Define the name of the text file you want to create
        String fileName = file_name;

        // Get the root folder reference
        StorageReference folderRef = storageRef;

        // Create nested directories if they exist in the folder_path_branches array
        for (String folderName : folder_path_branches) {
            if (!folderName.isEmpty()) {
                // Create a reference to the next level folder
                folderRef = folderRef.child(folderName);
            }
        }

        // Create a reference to the file inside the final folder
        StorageReference fileRef = folderRef.child(fileName);

        byte[] data = fileContent.getBytes();

        fileRef.putBytes(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // File upload successful
                        // Get the download URL of the uploaded file
                        Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                        downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    // Get the URL of the uploaded file
                                    Uri downloadUrl = task.getResult();
                                    String download_url_string = downloadUrl.toString();
                                    X0002_DOWNLOAD_URL.set(download_url_string);
                                } else {
                                    X0002_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                X0002_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                X0002_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                        X0002_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        X0002_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                });

    }



    public static void createWrite_File(String folder_path, String file_name, ArrayList<String> file_contents) {
        AtomicReference<String> download_url_string = new AtomicReference<>(null);
        //This code wont recreate the file if it already existed
        String[] folder_path_branches = folder_path.split("/");

        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Concatenate the ArrayList into a single string with newline characters
        StringBuilder fileContentBuilder = new StringBuilder();
        for (int i = 0; i < file_contents.size(); i++) {
            String line = file_contents.get(i);
            fileContentBuilder.append(line).append("\n");
        }
        String fileContent = fileContentBuilder.toString().trim(); // Remove the last newline character

        // Define the name of the text file you want to create
        String fileName = file_name;

        // Get the root folder reference
        StorageReference folderRef = storageRef;

        // Create nested directories if they exist in the folder_path_branches array
        for (String folderName : folder_path_branches) {
            if (!folderName.isEmpty()) {
                // Create a reference to the next level folder
                folderRef = folderRef.child(folderName);
            }
        }

        // Create a reference to the file inside the final folder
        StorageReference fileRef = folderRef.child(fileName);

        byte[] data = fileContent.getBytes();

        fileRef.putBytes(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // File upload successful
                        // Get the download URL of the uploaded file
                        Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                        downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task){
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                    }
                });

    }

    public static void getDownloadURL_X0003(String folder_path, String file_name) {
        String[] folder_path_branches = (folder_path != null) ? folder_path.split("/") : new String[0];

        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Get the root folder reference
        StorageReference folderRef = storageRef;

        // Create nested directories if they exist in the folder_path_branches array
        for (String folderName : folder_path_branches) {
            if (!folderName.isEmpty()) {
                // Create a reference to the next level folder
                folderRef = folderRef.child(folderName);
            }
        }

        // Create a reference to the file inside the final folder
        StorageReference fileRef = folderRef.child(file_name);

        // Get the download URL of the file
        fileRef.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadUrl) {
                        // Get the URL of the file
                        String download_url_string = downloadUrl.toString();
                        X0003_DOWNLOAD_URL.set(download_url_string);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        X0003_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        X0003_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                });
    }

    public static void getDownloadURL_X0006(String file_name) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Create a reference to the file in the root directory
        StorageReference fileRef = storageRef.child(file_name);

        // Get the download URL of the file
        fileRef.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadUrl) {
                        // Get the URL of the file
                        String download_url_string = downloadUrl.toString();
                        X0006_DOWNLOAD_URL.set(download_url_string);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        X0006_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        X0006_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                });
    }




    public static void createDirectory(String folder_path) {
        String[] folder_path_branches = folder_path.split("/");

        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Get the root folder reference
        StorageReference folderRef = storageRef;

        // Create nested directories if they exist in the folder_path_branches array
        for (String folderName : folder_path_branches) {
            if (!folderName.isEmpty()) {
                // Create a reference to the next level folder
                folderRef = folderRef.child(folderName);
            }
        }

    }



    public static void createEmptyFile_X0004(String folder_path, String file_name) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Get the root folder reference
        StorageReference folderRef = storageRef;

        // Create nested directories if they exist in the folder_path array
        String[] folder_path_branches = folder_path.split("/");
        for (String folderName : folder_path_branches) {
            if (!folderName.isEmpty()) {
                // Create a reference to the next level folder
                folderRef = folderRef.child(folderName);
            }
        }

        // Define the name of the text file you want to create
        String fileName = file_name;

        // Create a reference to the file inside the final folder
        StorageReference fileRef = folderRef.child(fileName);

        // Create an empty file (without contents)
        byte[] emptyData = new byte[0];

        fileRef.putBytes(emptyData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // File upload successful
                        // Get the download URL of the uploaded file
                        Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                        downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    // Get the URL of the uploaded file
                                    Uri downloadUrl = task.getResult();
                                    String download_url_string = downloadUrl.toString();
                                    X0004_DOWNLOAD_URL.set(download_url_string);
                                } else {
                                    X0004_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                X0004_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                X0004_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                        X0004_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        X0004_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                });
    }

    public static void createEmptyFile(String folder_path, String file_name) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Get the root folder reference
        StorageReference folderRef = storageRef;

        // Create nested directories if they exist in the folder_path array
        String[] folder_path_branches = folder_path.split("/");
        for (String folderName : folder_path_branches) {
            if (!folderName.isEmpty()) {
                // Create a reference to the next level folder
                folderRef = folderRef.child(folderName);
            }
        }

        // Define the name of the text file you want to create
        String fileName = file_name;

        // Create a reference to the file inside the final folder
        StorageReference fileRef = folderRef.child(fileName);

        // Create an empty file (without contents)
        byte[] emptyData = new byte[0];

        fileRef.putBytes(emptyData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // File upload successful
                        // Get the download URL of the uploaded file
                        Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                        downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                    }
                });
    }


    public static void createEmptyFile_X0005(String fileName) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Create a reference to the file you want to create
        final StorageReference fileRef = storageRef.child(fileName);

        // Check if the file already exists
        fileRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
            @Override
            public void onSuccess(StorageMetadata storageMetadata) {
                // File already exists, skip the upload process
                // Get the download URL of the existing file
                Task<Uri> downloadUrlTask = fileRef.getDownloadUrl();
                downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            // Get the URL of the existing file
                            Uri downloadUrl = task.getResult();
                            String downloadUrlString = downloadUrl.toString();
                            X0005_DOWNLOAD_URL.set(downloadUrlString);
                        } else {
                            X0005_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                            // Handle the error
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        X0005_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        X0005_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // File does not exist or some other error occurred while checking the metadata
                // Proceed with uploading the empty byte array
                byte[] emptyData = new byte[0];
                fileRef.putBytes(emptyData)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // File upload successful
                                // Get the download URL of the uploaded file
                                Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            // Get the URL of the uploaded file
                                            Uri downloadUrl = task.getResult();
                                            String downloadUrlString = downloadUrl.toString();
                                            X0005_DOWNLOAD_URL.set(downloadUrlString);
                                        } else {
                                            X0005_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                                            // Handle the error
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        X0005_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                                X0005_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                X0005_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
                            }
                        });
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                X0005_DOWNLOAD_URL.set(NULL_RESULT_IDENTIFIER);
            }
        });
    }


    public static void createEmptyFile(String fileName) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Create a reference to the file you want to create
        final StorageReference fileRef = storageRef.child(fileName);

        // Check if the file already exists
        fileRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
            @Override
            public void onSuccess(StorageMetadata storageMetadata) {
                // File already exists, do nothing
                // You may add additional logic here if needed
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // File does not exist or some other error occurred while checking the metadata
                // Proceed with uploading the empty byte array
                byte[] emptyData = new byte[0];
                fileRef.putBytes(emptyData)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // File upload successful
                                // Get the download URL of the uploaded file
                                Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        // Handle the completion if needed
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                            }
                        });
            }
        });
    }




    public static void createEmptyFiles(String folder_path, ArrayList<String> file_names) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Get the root folder reference
        StorageReference folderRef = storageRef;

        // Create nested directories if they exist in the folder_path array
        String[] folder_path_branches = folder_path.split("/");
        for (String folderName : folder_path_branches) {
            if (!folderName.isEmpty()) {
                // Create a reference to the next level folder
                folderRef = folderRef.child(folderName);
            }
        }

        // Create an empty file (without contents)
        byte[] emptyData = new byte[0];

        // Loop through the list of file names and upload each file
        for (String file_name : file_names) {
            // Define the name of the text file you want to create
            String fileName = file_name;

            // Create a reference to the file inside the final folder
            final StorageReference fileRef = folderRef.child(fileName);

            // Check if the file already exists
            fileRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                @Override
                public void onSuccess(StorageMetadata storageMetadata) {
                    // File already exists, skip the upload process for this file
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // File does not exist or some other error occurred while checking the metadata
                    // Proceed with uploading the empty byte array
                    fileRef.putBytes(emptyData)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // File upload successful
                                    // Get the download URL of the uploaded file
                                    Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                    downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {
                                            // File upload is complete for this file, you can add any further handling if needed.
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle the error
                                }
                            });
                }
            });
        }
    }


    public static void createEmptyFiles_WaitProgress(String folder_path, ArrayList<String> file_names) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Get the root folder reference
        StorageReference folderRef = storageRef;

        // Create nested directories if they exist in the folder_path array
        String[] folder_path_branches = folder_path.split("/");
        for (String folderName : folder_path_branches) {
            if (!folderName.isEmpty()) {
                // Create a reference to the next level folder
                folderRef = folderRef.child(folderName);
            }
        }

        // Create an empty file (without contents)
        byte[] emptyData = new byte[0];

        // Loop through the list of file names and upload each file
        for (int index = 0; index < file_names.size(); index++) {
            String file_name = file_names.get(index);
            // Define the name of the text file you want to create
            String fileName = file_name;

            // Create a reference to the file inside the final folder
            final StorageReference fileRef = folderRef.child(fileName);

            // Check if the file already exists
            int finalIndex = index;
            fileRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                @Override
                public void onSuccess(StorageMetadata storageMetadata) {
                    // File already exists, skip the upload process for this file
                    I_0001_WAIT_INTEGER.set(finalIndex);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // File does not exist or some other error occurred while checking the metadata
                    // Proceed with uploading the empty byte array
                    fileRef.putBytes(emptyData)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // File upload successful
                                    // Get the download URL of the uploaded file
                                    Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
                                    downloadUrlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {
                                            // File upload is complete for this file, you can add any further handling if needed.
                                            I_0001_WAIT_INTEGER.set(finalIndex);
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle the error
                                    I_0001_WAIT_INTEGER.set(finalIndex);
                                }
                            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    I_0001_WAIT_INTEGER.set(finalIndex);
                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    I_0001_WAIT_INTEGER.set(finalIndex);
                                }
                            });
                }
            });
        }
    }


    public static void deleteFile(String directory, String filename) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Create a reference to the file you want to delete
        StorageReference fileToDeleteRef = storageRef.child(directory + "/" + filename);

        // Delete the file
        fileToDeleteRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // File deleted successfully
                        // You can perform any additional operations here if needed
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                    }
                });
    }

    public static void deleteFile(String filename) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Create a reference to the file you want to delete (located in the root directory)
        StorageReference fileToDeleteRef = storageRef.child(filename);

        // Delete the file
        fileToDeleteRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // File deleted successfully
                        // You can perform any additional operations here if needed
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
                    }
                });
    }

    public static void deleteFile(ArrayList<String> filenames) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        for (String filename : filenames) {
            // Create a reference to the file you want to delete (located in the root directory)
            StorageReference fileToDeleteRef = storageRef.child(filename);

            // Delete the file
            fileToDeleteRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        // File deleted successfully
                        // You can perform any additional operations here if needed
                    } else {
                        // Handle the error
                    }
                }
            });
        }
    }

    public static void deleteFile(String directory, ArrayList<String> filenames) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Loop through the filenames and delete each file
        for (String filename : filenames) {
            // Create a reference to the file you want to delete
            StorageReference fileToDeleteRef = storageRef.child(directory + "/" + filename);

            // Delete the file
            fileToDeleteRef.delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // File deleted successfully
                            // You can perform any additional operations here if needed
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle the error
                        }
                    });
        }
    }






    public static void clearDirectory_ImmediateFiles(String directory) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Create a reference to the directory you want to delete
        StorageReference directoryRef = storageRef.child(directory);

        // List all items (files and subdirectories) within the directory
        directoryRef.listAll()
                .addOnCompleteListener(new OnCompleteListener<ListResult>() {
                    @Override
                    public void onComplete(@NonNull Task<ListResult> task) {
                        if (task.isSuccessful()) {
                            // Delete each item (file or subdirectory) in the directory
                            for (StorageReference item : task.getResult().getItems()) {
                                item.delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Item (file or subdirectory) deleted successfully
                                                // You can perform any additional operations here if needed
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Handle the error
                                            }
                                        });
                            }
                        } else {
                            // Handle the error
                        }
                    }
                });
    }





    public static void deleteFile_WaitProgress(AtomicBoolean is_finished, String directory, String filename) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();

                // Create a reference to the file you want to delete
                StorageReference fileToDeleteRef = storageRef.child(directory + "/" + filename);

                // Delete the file
                fileToDeleteRef.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // File deleted successfully
                                // You can perform any additional operations here if needed
                                is_finished.set(true);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                                is_finished.set(true);
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                is_finished.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                is_finished.set(true);
                            }
                        });
            }
        }).start();


    }

    public static void deleteFile_WaitProgress(AtomicBoolean is_finished, String filename) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();

                // Create a reference to the file you want to delete (located in the root directory)
                StorageReference fileToDeleteRef = storageRef.child(filename);

                // Delete the file
                fileToDeleteRef.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // File deleted successfully
                                // You can perform any additional operations here if needed
                                is_finished.set(true);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle the error
                                is_finished.set(true);
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                is_finished.set(true);
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                is_finished.set(true);
                            }
                        });
            }
        }).start();


    }

    public static void deleteFile_WaitProgress(AtomicBoolean is_finished, ArrayList<String> filenames) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Initialize Firebase Storage
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();

                for (int index = 0; index < filenames.size(); index++) {
                    String filename = filenames.get(index);
                    // Create a reference to the file you want to delete (located in the root directory)
                    StorageReference fileToDeleteRef = storageRef.child(filename);

                    // Delete the file
                    int finalIndex = index;
                    fileToDeleteRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                // File deleted successfully
                                // You can perform any additional operations here if needed
                            } else {
                                // Handle the error
                            }

                            if(finalIndex == (filenames.size()-1)){
                                is_finished.set(true);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if(finalIndex == (filenames.size()-1)){
                                is_finished.set(true);
                            }
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            if(finalIndex == (filenames.size()-1)){
                                is_finished.set(true);
                            }
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            is_finished.set(true);
                        }
                    });
                }
            }
        }).start();


    }

    public static void deleteFile_WaitProgress(AtomicBoolean is_finished, String directory, ArrayList<String> filenames) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Loop through the filenames and delete each file
        for (int index = 0; index < filenames.size(); index++) {
            String filename = filenames.get(index);
            // Create a reference to the file you want to delete
            StorageReference fileToDeleteRef = storageRef.child(directory + "/" + filename);

            // Delete the file
            int finalIndex = index;
            fileToDeleteRef.delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // File deleted successfully
                            // You can perform any additional operations here if needed
                            if(finalIndex == (filenames.size()-1)){
                                is_finished.set(true);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle the error
                            if(finalIndex == (filenames.size()-1)){
                                is_finished.set(true);
                            }
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(finalIndex == (filenames.size()-1)){
                                is_finished.set(true);
                            }
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            if(finalIndex == (filenames.size()-1)){
                                is_finished.set(true);
                            }
                        }
                    });
        }
    }






    public static void clearDirectory_ImmediateFiles_WaitProgress(AtomicBoolean is_finished, String directory) {
        // Initialize Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Create a reference to the directory you want to delete
        StorageReference directoryRef = storageRef.child(directory);

        // List all items (files and subdirectories) within the directory
        directoryRef.listAll()
                .addOnCompleteListener(new OnCompleteListener<ListResult>() {
                    @Override
                    public void onComplete(@NonNull Task<ListResult> task) {
                        if (task.isSuccessful()) {
                            // Delete each item (file or subdirectory) in the directory
                            List<StorageReference> items = task.getResult().getItems();
                            for (int index = 0; index < items.size(); index++) {
                                int finalIndex = index;
                                StorageReference item = items.get(index);
                                item.delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Item (file or subdirectory) deleted successfully
                                                // You can perform any additional operations here if needed
                                                if(finalIndex == (items.size()-1)){
                                                    is_finished.set(true);
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Handle the error
                                                if(finalIndex == (items.size()-1)){
                                                    is_finished.set(true);
                                                }
                                            }
                                        }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(finalIndex == (items.size()-1)){
                                                    is_finished.set(true);
                                                }
                                            }
                                        }).addOnCanceledListener(new OnCanceledListener() {
                                            @Override
                                            public void onCanceled() {
                                                if(finalIndex == (items.size()-1)){
                                                    is_finished.set(true);
                                                }
                                            }
                                        });
                            }
                        } else {
                            // Handle the error
                            is_finished.set(true);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        is_finished.set(true);
                    }
                }).addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        is_finished.set(true);
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        is_finished.set(true);
                    }
                });
    }






}
