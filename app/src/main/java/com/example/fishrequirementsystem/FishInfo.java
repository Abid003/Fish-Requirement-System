package com.example.fishrequirementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fishrequirementsystem.adapter.MyAdapter;

public class FishInfo extends AppCompatActivity {

    int [] images = {
            R.drawable.gold_fish,
            R.drawable.angel_fish,
            R.drawable.shark
            ,R.drawable.zebra_danios,
            R.drawable.guppies,
            R.drawable.betta_fish
            ,R.drawable.molly_fish,
            R.drawable.oranda_goldfish,
            R.drawable.neon_tetras
            ,R.drawable.sucker_fish
            ,R.drawable.tiger_barb
    };

    MyAdapter myAdapter;

    String [] title, description;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_info);

        FishInfo.this.setTitle("Fish Info");

        recyclerView = findViewById(R.id.recyclerViewId);


        title = getResources().getStringArray(R.array.fish_name);
        description = getResources().getStringArray(R.array.fish_Description);

        myAdapter = new MyAdapter(this,title,description,images);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}