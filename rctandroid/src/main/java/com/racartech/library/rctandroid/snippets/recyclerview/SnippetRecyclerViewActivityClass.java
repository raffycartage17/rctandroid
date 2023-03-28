package com.racartech.library.rctandroid.snippets.recyclerview;

import android.content.Context;
import android.widget.Toast;

public class SnippetRecyclerViewActivityClass {
    public static void test(Context app_context){
        Toast.makeText(app_context, "Snippet Class Test", Toast.LENGTH_SHORT).show();
    }
    /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
     */

}


/*


import android.os.Bundle;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.racartech.library.rctandroid19.layout.RCT19PreLoadingLinearLayoutManager;
import java.util.ArrayList;
import java.util.Objects;

import com.racartech.app.rctandroid19.R;

public class Window1 extends AppCompatActivity {
    private RecyclerView recycler_view;
    ArrayList<String> dataset = new ArrayList<>();
    Window1RecyclerAdapter adapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_1);
        recycler_view = findViewById(R.id.window_1_recyclerview);
        //initialize dataset here
        displayEntries();
    }

    public void displayEntries(){
        new Thread(new Runnable() {
            @Override
            public void run(){
                //This implementation shall use a a dataset from the other class/or this class. Ex. public static ArrayList<Object> dataset;
                int preloaded_messages = 10;
                recycler_view.setLayoutManager(
                        new RCT19PreLoadingLinearLayoutManager(getApplicationContext(), preloaded_messages));
                Parcelable recyclerViewState;
                recyclerViewState = Objects.requireNonNull(recycler_view.getLayoutManager()).onSaveInstanceState();
                recycler_view.setItemViewCacheSize(50);

                adapter = new Window1RecyclerAdapter(Window1.this,Window1.this,getApplicationContext(),dataset);
                recycler_view.setAdapter(adapter);
                recycler_view.getLayoutManager().onRestoreInstanceState(recyclerViewState);

            }
        }).start();
    }

    public void addEntry(ArrayList<String> new_entries){
        int startPosition = dataset.size();
        dataset.addAll(new_entries);
        adapter.notifyItemRangeInserted(startPosition, new_entries.size());
    }


    public void addEntry(String new_entry){
        dataset.add(new_entry);
        adapter.notifyItemInserted(dataset.size() - 1);
    }

    public void insertEntry(String new_entry, int position){
        dataset.add(position, new_entry);
        adapter.notifyItemInserted(position);
    }

    public void deleteEntry(String target_entry){
        int position = dataset.indexOf(target_entry);
        if (position >= 0) {
            dataset.remove(position);
            adapter.notifyItemRemoved(position);
        }
    }

    public void deleteEntries(ArrayList<String> target_entries){
        for (String entry : target_entries) {
            int position = dataset.indexOf(entry);
            if (position >= 0) {
                dataset.remove(position);
                adapter.notifyItemRemoved(position);
            }
        }
    }

    public void deleteEntries_ByPosition(ArrayList<Integer> positions){
        for (int i = positions.size() - 1; i >= 0; i--) {
            int position = positions.get(i);
            if (position >= 0 && position < dataset.size()) {
                dataset.remove(position);
                adapter.notifyItemRemoved(position);
            }
        }
    }

    public void deleteEntries_ByPosition(int position){
        dataset.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void deleteEntry(int position){
        dataset.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void updateEntry(String updated_entry, int position) {
        dataset.set(position, updated_entry);
        adapter.notifyItemChanged(position);
    }

    public int getDatasetSize() {
        return dataset.size();
    }

    public boolean isDatasetEmpty() {
        return dataset.isEmpty();
    }

    public void clearDataset() {
        dataset.clear();
        adapter.notifyDataSetChanged();
    }

    public void insertEntries(ArrayList<String> new_entries, int position) {
        dataset.addAll(position, new_entries);
        adapter.notifyItemRangeInserted(position, new_entries.size());
    }

    public String getEntry(int position) {
        return dataset.get(position);
    }





    public void syncEntriesToDataset(){
        adapter.notifyDataSetChanged();
    }
}


*/
