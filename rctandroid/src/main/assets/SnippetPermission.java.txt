package com.racartech.library.rctandroid.snippets;

import android.content.Context;
import android.widget.Toast;

public class SnippetPermission {

    public static void test(Context context){
        Toast.makeText(context, "Android Basic", Toast.LENGTH_SHORT).show();
        /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
        */
    }



    /*

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <uses-feature android:name="android.hardware.camera" />

    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.INTERNET" />

     */

    //Code for other permissions
    /*
    RCTpermission.allowPermissions(this,new String[]{
                Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
        },0);
     */


    //Codes for Manage External Storage
    /*Activity Code

    //Variables
    private static final int MANAGE_EXTERNAL_STORAGE_PERMISSION_CODE = 100;
    private static final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 101;
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 102;

    //Code
    private void promptUserAllowPermission(){
        if(!Environment.isExternalStorageManager()){
            Context app_context = MainActivity.this;
            Dialog request_dialog = new Dialog(app_context);
            request_dialog.setContentView(R.layout.standard_dialog_box);
            request_dialog.setCancelable(true);

            TextView dialog_title = request_dialog.findViewById(R.id.standard_dialog_title_textview);
            TextView dialog_description = request_dialog.findViewById(R.id.standard_dialog_description_textview);
            Button ok_button = request_dialog.findViewById(R.id.standard_dialog_right_operation_button);
            Button cancel_button = request_dialog.findViewById(R.id.standard_dialog_left_operation_button);
            ok_button.setText(app_context.getString(R.string.OK));
            cancel_button.setText(app_context.getString(R.string.Cancel));
            dialog_title.setText(app_context.getString(R.string.app_name));
            dialog_description.setText(app_context.getString(R.string.access_all_files_permission_dialog_request_description));
            request_dialog.show();
            ok_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent new_intent = new Intent(ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                    startActivityForResult(new_intent, MANAGE_EXTERNAL_STORAGE_PERMISSION_CODE);
                    request_dialog.dismiss();
                }
            });
            cancel_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RCTnotifications.makeToast_SHORT(app_context, "Permission Request Denied");
                    request_dialog.dismiss();
                }
            });
        }
        checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE_PERMISSION_CODE);
        checkPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE_PERMISSION_CODE);



    }

    private void checkPermission(String permission, int request_code){
        if(ContextCompat.checkSelfPermission(MainActivity.this,permission)== PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{permission},request_code);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


     */


    /* standard_dialog_box.xml

    <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/standard_dialog_box_root_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/sdb_background">

    <TextView
        android:id="@+id/standard_dialog_title_textview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:textColor="@color/black"
        android:textSize="20sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


    <TableRow
        android:id="@+id/standard_dialog_operation_tablerow"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_marginTop="32dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/standard_dialog_description_textview">

        <Button
            android:id="@+id/standard_dialog_left_operation_button"
            android:layout_width="800dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:background="@color/sdb_background"
            android:textColor="@color/blue"
            tools:ignore="SpeakableTextPresentCheck" />

        <Button
            android:id="@+id/standard_dialog_right_operation_button"
            android:layout_width="800dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:background="@color/sdb_background"
            android:textColor="@color/blue"
            tools:ignore="SpeakableTextPresentCheck" />
    </TableRow>

    <TextView
        android:id="@+id/standard_dialog_description_textview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginTop="32dp"
        android:layout_marginEnd="16dp"
        android:textColor="@color/black"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/standard_dialog_title_textview" />


</androidx.constraintlayout.widget.ConstraintLayout>


     */

    /*Color and String
    <color name="sdb_background">#ebf7f6</color>

    <string name="OK">OK</string>
    <string name="Cancel">Cancel</string>
    <string name="access_all_files_permission_dialog_request_description">RC File Explorer need the permission to manage files in your device to function properly</string>

     */





}
