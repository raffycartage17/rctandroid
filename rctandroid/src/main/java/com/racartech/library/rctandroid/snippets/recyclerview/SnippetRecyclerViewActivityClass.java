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


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.racartech.library.rctandroid.layout.RCTpreLoadingLinearLayoutManager;

import java.util.ArrayList;
import java.util.Objects;

public class SuggestionsListActivity extends AppCompatActivity {


    RecyclerView recycler_view;
    SuggestionsListAdapter adapter;
    ArrayList<SimpleSuggestionsData> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestions_user_list_layout);
        recycler_view = findViewById(R.id.sull_recycler_view);
    }




    public void displayEntries(){
        new Thread(new Runnable() {
            @Override
            public void run(){
                //This implementation shall use a a dataset from the other class/or this class. Ex. public static ArrayList<Object> dataset;
                int preloaded_items = 20;
                recycler_view.setLayoutManager(
                        new RCTpreLoadingLinearLayoutManager(getApplicationContext(), preloaded_items));
                Parcelable recyclerViewState;
                recyclerViewState = Objects.requireNonNull(recycler_view.getLayoutManager()).onSaveInstanceState();
                recycler_view.setItemViewCacheSize(50);

                adapter = new SuggestionsListAdapter(SuggestionsListActivity.this,SuggestionsListActivity.this,dataset);
                recycler_view.setAdapter(adapter);
                recycler_view.getLayoutManager().onRestoreInstanceState(recyclerViewState);

            }
        }).start();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void syncEntriesToDataset(){
        adapter.notifyDataSetChanged();
    }




}


*/
