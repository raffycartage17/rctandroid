package com.racartech.library.rctandroid.snippets.roomsqlite;

import android.content.Context;
import android.widget.Toast;

public class SnippetRoomSQLite_EntryDAO {


    public static void test(Context context){
        Toast.makeText(context, "Test Method", Toast.LENGTH_SHORT).show();
        /*
            NOTE : this is a snippet class intended to help developers to fasten there production
            by providing them easy to use code common code snippets
        */
    }

/*
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EntryDAO {
    @Insert
    void insert(DatabaseEntry databaseEntry);

    @Update
    void update(DatabaseEntry databaseEntry);

    @Delete
    void delete(DatabaseEntry databaseEntry);

    @Query("SELECT * FROM DatabaseEntry")
    List<DatabaseEntry> getAllEntries();

    @Query("SELECT * FROM DatabaseEntry WHERE id = :entry_id")
    DatabaseEntry getEntryById(int entry_id);

    @Query("SELECT EXISTS(SELECT 1 FROM DatabaseEntry WHERE id = :entry_id)")
    boolean doesEntryExist(int entry_id);

    @Query("DELETE FROM DatabaseEntry")
    void deleteAllEntries();

    @Query("DELETE FROM DatabaseEntry WHERE id = :entry_id")
    void deleteEntryById(int entry_id);



}
 */

}
