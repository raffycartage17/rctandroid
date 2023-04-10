package com.racartech.library.rctandroid.snippets;

import android.content.Context;
import android.widget.Toast;

public class SnippetFileProvider {


    public static void test(Context context){
        Toast.makeText(context, "Android Basic", Toast.LENGTH_SHORT).show();
        /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
        */
    }

    //res/xml/provider_paths.xml
    /*
            <?xml version="1.0" encoding="utf-8"?>
        <paths xmlns:android="http://schemas.android.com/apk/res/android">
            <root-path name="root" path="." />
            <external-path
                name="external"
                path="." />
            <external-files-path
                name="external_files"
                path="." />
            <cache-path
                name="cache"
                path="." />
            <external-cache-path
                name="external_cache"
                path="." />
            <files-path
                name="files"
                path="." />
        </paths>
     */


    /* Manifest Code - Add after the activity-block

    <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}.provider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/provider_paths" />
        </provider>

     */



}
