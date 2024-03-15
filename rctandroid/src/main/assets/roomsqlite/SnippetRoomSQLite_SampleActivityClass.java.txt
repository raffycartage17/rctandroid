package com.racartech.library.rctandroid.snippets.roomsqlite;

import android.content.Context;
import android.widget.Toast;

public class SnippetRoomSQLite_SampleActivityClass {

    public static void test(Context context){
        Toast.makeText(context, "Test Method", Toast.LENGTH_SHORT).show();
        /*
            NOTE : this is a snippet class intended to help developers to fasten there production
            by providing them easy to use code common code snippets
        */
    }

    /*
Needed Import = import androidx.room.Room;


String database_name = "test_database.db";

        MyDatabase database = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, database_name)
                .build();
        EntryDAO entry_dao = database.EntryDAO();


        //Database access should be done inside another thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                //entry_dao.deleteAllEntries();
                if(!entry_dao.doesEntryExist(1)) {
                    DatabaseEntry rac_user = new DatabaseEntry();
                    rac_user.id = 1;
                    rac_user.first_name = "Rafael";
                    rac_user.middle_name = "Andaya";
                    rac_user.last_name = "Cartagena";
                    rac_user.age = 22;
                    entry_dao.insert(rac_user);
                }
                if(!entry_dao.doesEntryExist(2)) {
                    DatabaseEntry jed_user = new DatabaseEntry();
                    jed_user.id = 2;
                    jed_user.first_name = "Jonalyn";
                    jed_user.middle_name = "Ecija";
                    jed_user.last_name = "Dela Cruz";
                    jed_user.age = 20;
                    entry_dao.insert(jed_user);
                }
            }
        }).start();








        new Thread(new Runnable() {
            @Override
            public void run() {

                //Database Access should be done in another thread, like in this example
                List<DatabaseEntry> all_entries = entry_dao.getAllEntries();

                DatabaseEntry rac_entry = entry_dao.getEntryById(1);
                DatabaseEntry jed_entry = entry_dao.getEntryById(2);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run(){
                        String toast_text_1 = "Entry : ".concat(
                                        String.valueOf(rac_entry.id)).concat("   Name : ").
                                concat(rac_entry.first_name).concat(" ").
                                concat(rac_entry.middle_name).concat(" ").
                                concat(rac_entry.last_name).concat(" ").
                                concat(String.valueOf(rac_entry.age));

                        String toast_text_2 = "Entry : ".concat(
                                        String.valueOf(jed_entry.id)).concat("   Name : ").
                                concat(jed_entry.first_name).concat(" ").
                                concat(jed_entry.middle_name).concat(" ").
                                concat(jed_entry.last_name).concat(" ").
                                concat(String.valueOf(jed_entry.age));


                        TextView rac_tv = new TextView(getApplicationContext());
                        TextView jed_tv = new TextView(getApplicationContext());



                        rac_tv.setText(toast_text_1);
                        rac_tv.setTextColor(Color.BLACK);
                        rac_tv.setTextSize(15);

                        jed_tv.setText(toast_text_2);
                        jed_tv.setTextColor(Color.BLACK);
                        jed_tv.setTextSize(15);


                        linearLayout.addView(rac_tv);
                        linearLayout.addView(jed_tv);
                    }
                });
            }
        }).start();





 */


}



