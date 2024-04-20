package com.racartech.app.rctandroidlts.functionbuttons;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.racartech.app.rctandroidlts.R;

import java.util.ArrayList;

public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.ViewHolder>{
    private final Context app_context;
    private final Activity current_activity;
    private ArrayList<String> dataset;
    public TestRecyclerAdapter(Activity current_activity, Context app_context, ArrayList<String> dataset){
        this.app_context = app_context;
        this.current_activity = current_activity;
        this.dataset = dataset;
    }


    @NonNull
    @Override
    public TestRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(app_context).inflate(R.layout.tra_entry_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestRecyclerAdapter.ViewHolder holder, int position) {
        int adapter_position = holder.getAdapterPosition();
        holder.main_text.setText(dataset.get(adapter_position));
        holder.number_text.setText("Index : ".concat(String.valueOf(adapter_position)));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView main_text;
        TextView number_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main_text = itemView.findViewById(R.id.tra_main_textview);
            number_text = itemView.findViewById(R.id.tra_number_textview);
        }
    }

}
