package com.example.fishrequirementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Fish Requirement System");

        Button phButton = findViewById(R.id.PHchceckBtn);
        Button timeSchedule = findViewById(R.id.viewScheduleBtn);
        Button feedFishBtn = findViewById(R.id.feedFishBtn);
        Button fishInfo = findViewById(R.id.fishInfoId);

        phButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PHCheckActivity.class);
                startActivity(intent);
            }
        });

        timeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Time_Schedule.class);
               startActivity(intent);
            }
        });

        feedFishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Feeding.class);
                startActivity(intent);
            }
        });

        fishInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FishInfo.class);
                startActivity(intent);

            }
        });

    }
}