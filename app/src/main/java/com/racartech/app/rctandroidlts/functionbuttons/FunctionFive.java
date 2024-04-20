package com.racartech.app.rctandroidlts.functionbuttons;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.datatypes.RCTstring;

import java.util.ArrayList;
import java.util.Objects;

public class FunctionFive extends AppCompatActivity {


    RecyclerView recycler_view;
    TestRecyclerAdapter adapter;
    Button f1,f2,f3;
    ArrayList<String> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_five_activity_layout);
        recycler_view = findViewById(R.id.f5_recycler_view);
        f1 = findViewById(R.id.f5_f1_button);
        f2 = findViewById(R.id.f5_f2_button);
        f3 = findViewById(R.id.f5_f3_button);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int index = 0; index<10000; index++){
                            dataset.add(RCTstring.randomString(30,30));
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run(){
                                syncEntriesToDataset();
                                recycler_view.scrollToPosition(dataset.size()-1);
                            }
                        });
                    }
                }).start();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                displayEntries();

                for(int index = 0; index<100000; index++){
                    dataset.add(RCTstring.randomString(30,30));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run(){
                        syncEntriesToDataset();
                    }
                });
            }
        }).start();


    }




    public void displayEntries(){
        new Thread(new Runnable() {
            @Override
            public void run(){
                /*
                recycler_view.setLayoutManager(
                        new RCTpreLoadingLinearLayoutManager(getApplicationContext(), preloaded_items));
                 */
                recycler_view.setLayoutManager(new LinearLayoutManager(
                        FunctionFive.this,
                        LinearLayoutManager.VERTICAL,
                        false)
                );
                Parcelable recyclerViewState;
                recyclerViewState = Objects.requireNonNull(recycler_view.getLayoutManager()).onSaveInstanceState();
                recycler_view.setItemViewCacheSize(50);

                adapter = new TestRecyclerAdapter(FunctionFive.this, FunctionFive.this,dataset);
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
