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


import java.util.ArrayList;

public class SuggestionsListAdapter extends RecyclerView.Adapter<SuggestionsListAdapter.ViewHolder>{
    private final Context app_context;
    private final Activity current_activity;
    private ArrayList<String> dataset;
    public SuggestionsListAdapter(Activity current_activity, Context app_context, ArrayList<String> dataset){
        this.app_context = app_context;
        this.current_activity = current_activity;
        this.dataset = dataset;
    }


    @NonNull
    @Override
    public SuggestionsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(app_context).inflate(R.layout.entry_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionsListAdapter.ViewHolder holder, int position) {
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
