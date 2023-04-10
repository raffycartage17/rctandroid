package com.racartech.library.rctandroid.snippets;

import android.content.Context;
import android.widget.Toast;

public class SnippetDialog {



    public static void test(Context context){
        Toast.makeText(context, "Android Basic", Toast.LENGTH_SHORT).show();
        /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
        */
    }



    /*
        Dialog dialog = new Dialog(Window1.this);
                    dialog.setContentView(R.layout.standard_dialog_box);
                    dialog.setCancelable(true);
                    dialog.show();
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
}
