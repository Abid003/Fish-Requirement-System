package com.example.fishrequirementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PHCheckActivity extends AppCompatActivity {

    float phValue;


    TextView phTextview;
    TextView conditionTextview;
    TextView recommendationTextview;

    DatabaseReference dref;
    String sensorValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phcheck);

        PHCheckActivity.this.setTitle("Aquarium Water Condition");

        ///find components
        phTextview = findViewById(R.id.phTextviwId);
        conditionTextview = findViewById(R.id.conditionTextvieId);
        recommendationTextview = findViewById(R.id.recommedationTextviewId);


        dref = FirebaseDatabase.getInstance().getReference();

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sensorValue = snapshot.child("PH").getValue().toString();
                setValuesToTextView(sensorValue);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setValuesToTextView(String sensorValue) {

        phValue =Float.parseFloat(sensorValue);

        String conditionText="hm";
        String recommendationText = "hm";

        if(phValue >= 7.0 && phValue <= 9){
            conditionText = "good";
            recommendationText = "no changes needed";
        }
        else if(phValue < 7.0 &&  phValue >= 4.5){
            conditionText = "Not good, The water is little acidic";
            recommendationText = "Change water or add some Basic salt";
        }
        else if(phValue<4.5){
            conditionText = "Very bad, The water is very acidic";
            recommendationText = "Change water";
        }
       else if(phValue>9 && phValue<=12.0){
            conditionText = "Not good, The water is little bacic";
            recommendationText = "Change water or make water little acidic";
        }

       else if(phValue>12.0){
            conditionText = "Very bad, The water is very basic";
            recommendationText = "Change water";
        }
        conditionTextview.setText(conditionText);
        recommendationTextview.setText(recommendationText);
        phTextview.setText(sensorValue);
    }
}