package com.racartech.library.rctandroid.snippets;

import android.content.Context;
import android.widget.Toast;

public class SnippetBasic {

    public static void test(Context context){
        Toast.makeText(context, "Android Basic", Toast.LENGTH_SHORT).show();
        /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
        */
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
