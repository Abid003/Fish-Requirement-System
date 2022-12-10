package com.example.fishrequirementsystem.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fishrequirementsystem.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    String [] title, description;
    int [] images;


    public MyAdapter(Context context, String[] title, String[] description, int[] images) {
        this.context = context;
        this.title = title;
        this.description = description;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view =  layoutInflater.inflate(R.layout.view_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.titleTextView.setText(title[position]);
        holder.fishImageView.setImageResource(images[position]);
        holder.descTextView.setText(description[position]);

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView, descTextView;
        ImageView fishImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextViewId);
            descTextView = itemView.findViewById(R.id.descTexViewId);
            fishImageView = itemView.findViewById(R.id.fishImageViewId);
        }
    }


}
