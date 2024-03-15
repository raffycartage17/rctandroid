package com.racartech.library.rctandroid.snippets

class KotlinTestClass {



    fun sum(a: Int, b: Int): Int {

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



        return a + b
    }







}