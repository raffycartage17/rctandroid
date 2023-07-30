package com.racartech.library.rctandroid.snippets.recyclerview;

import android.content.Context;
import android.widget.Toast;

public class SnippetReycyclerViewAdapter {
    public static void test(Context app_context){
        Toast.makeText(app_context, "Snippet Class Test", Toast.LENGTH_SHORT).show();
    }
    /*
    NOTE : this is a snippet class intended to help developers to fasten there production
           by providing them easy to use code common code snippets
     */
}

/*

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.racartech.app.rctandroid19.R;
import java.util.ArrayList;


public class Window1RecyclerAdapter extends RecyclerView.Adapter<Window1RecyclerAdapter.ViewHolder>{
    private final Context app_context;
    private final Activity current_activity;
    private Window1 window1;
    private ArrayList<String> dataset;
    public Window1RecyclerAdapter(Window1 window1, Activity current_activity, Context app_context, ArrayList<String> dataset){
        //Window1 is the ActivityClass of the recycler view
        this.app_context = app_context;
        this.current_activity = current_activity;
        this.window1 = window1;
        this.dataset = dataset;
    }


    @NonNull
    @Override
    public Window1RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(app_context).inflate(R.layout.sample_message_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Window1RecyclerAdapter.ViewHolder holder, int position) {
        int adapter_position = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}

 */
