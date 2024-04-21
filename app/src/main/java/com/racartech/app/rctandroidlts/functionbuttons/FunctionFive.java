package com.racartech.app.rctandroidlts.functionbuttons;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.racartech.app.rctandroidlts.R;
import com.racartech.library.rctandroid.datatypes.RCTstring;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class FunctionFive extends AppCompatActivity {


    RecyclerView recycler_view;
    TestRecyclerAdapter adapter;
    Button f1,f2,f3;
    ImageButton search_button, refresh_button;
    EditText textbox;
    volatile AtomicReference<ArrayList<String>> dataset_main = new AtomicReference<>(new ArrayList<>());
    volatile AtomicReference<ArrayList<String>> dataset_clone = new AtomicReference<>(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_five_activity_layout);
        recycler_view = findViewById(R.id.f5_recycler_view);
        f1 = findViewById(R.id.f5_f1_button);
        f2 = findViewById(R.id.f5_f2_button);
        textbox = findViewById(R.id.f5_textbox);
        search_button = findViewById(R.id.f5_search_button);
        refresh_button = findViewById(R.id.f5_refresh_button);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int index = 0; index<10; index++){
                            dataset_main.get().add(RCTstring.randomString(30,30));
                        }

                        dataset_clone.get().clear();
                        dataset_clone.get().addAll(dataset_main.get());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run(){
                                syncEntriesToDataset();
                            }
                        });
                    }
                }).start();
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String textbox_text = textbox.getText().toString();
                        dataset_clone.get().clear();
                        for(int index = 0; index<dataset_main.get().size(); index++){
                            if(dataset_main.get().get(index).contains(textbox_text)){
                                dataset_clone.get().add(dataset_main.get().get(index));
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                syncEntriesToDataset();
                                recycler_view.scrollToPosition(0);
                            }
                        });
                    }
                }).start();
            }
        });

        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dataset_clone.get().clear();
                        dataset_clone.get().addAll(dataset_main.get());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                syncEntriesToDataset();
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
                System.out.println("--------------------------------------");
                System.out.println("Initialize A");
                System.out.println("--------------------------------------");
                for(int index = 0; index<1000000; index++){
                    String new_string = RCTstring.randomString(30,30);
                    dataset_main.get().add(new_string);
                    dataset_clone.get().add(new_string);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run(){
                        System.out.println("--------------------------------------");
                        System.out.println("Initialize B");
                        System.out.println("--------------------------------------");
                        syncEntriesToDataset();
                        System.out.println("------------------------------");
                        System.out.println("Dataset Size : ".concat(String.valueOf(dataset_main.get().size())));
                        System.out.println("------------------------------");
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

                adapter = new TestRecyclerAdapter(FunctionFive.this, FunctionFive.this,dataset_clone);
                recycler_view.setAdapter(adapter);
                recycler_view.getLayoutManager().onRestoreInstanceState(recyclerViewState);

            }
        }).start();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void syncEntriesToDataset(){
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
