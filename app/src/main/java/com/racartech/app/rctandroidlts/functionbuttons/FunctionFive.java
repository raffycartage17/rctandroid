package com.racartech.app.rctandroidlts.functionbuttons;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.racartech.app.rctandroidlts.R;
import java.util.ArrayList;
import java.util.Objects;

public class FunctionFive extends AppCompatActivity {


    RecyclerView recycler_view;
    TestRecyclerAdapter adapter;
    ArrayList<String> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_five_activity_layout);
        recycler_view = findViewById(R.id.f5_recycler_view);
        dataset.add("AAAAA");
        dataset.add("BBBBB");
        dataset.add("CCCCC");
        dataset.add("DDDDD");
        dataset.add("EEEEE");
        dataset.add("FFFFF");
        dataset.add("GGGGG");
        dataset.add("HHHHH");
        dataset.add("HHHHH");
        dataset.add("IIIII");
        dataset.add("JJJJJ");

        displayEntries();

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
