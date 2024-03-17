package com.racartech.library.rctandroid.snippets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.racartech.library.rctandroid.R;

public class SnippetBasic {

    public static void test(Activity activity, Context context){
        System.out.println("--------------------Intent--------------------");
        Intent intent = new Intent(context, ReferenceTestClass.class);
        activity.startActivity(intent);

        System.out.println("--------------------Intent--------------------");

        System.out.println("R.id.snippet_test_root_layout should be the parent layout");

        ViewGroup parent = activity.findViewById(R.id.snippet_test_root_layout);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_standard_dialog_box, parent, false);


        System.out.println("--------------------Multi_Threading--------------------");
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run(){
                    }
                });
            }
        }).start();

    }





    /*

Intent intent = new Intent(getApplicationContext(), ClassName.class);
                startActivity(intent);

to put extras : intent.putExtra("extras_key","any type of data");   put this line in the middle of Intent intent and startActivity

 */


/* Layout Inflater

ViewGroup parent = findViewById(R.id.mm_scrollview_linearlayout);
View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.product_list_item, parent, false);

or

View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.product_list_item,null);

 */

    /*Permissions
        <uses-permission android:name = "android.permission.WRITE_EXTERNAL_STORAGE"/>

     */


    /*
new Thread(new Runnable() {
        @Override
        public void run() {

        }
    }).start();


new Thread(new Runnable() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run(){
                }
            });
        }
    }).start();



*/



}
