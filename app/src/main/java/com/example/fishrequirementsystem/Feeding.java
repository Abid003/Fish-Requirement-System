package com.example.fishrequirementsystem;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class Feeding extends AppCompatActivity {


    Button increaseBtn, decreaseBtn, feedBtn;
    TextView feedAmount,testTv;
    int amount;
    DatabaseReference dref;
    Timer timer;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding);

        Feeding.this.setTitle("Feed  Fish");

        dref = FirebaseDatabase.getInstance().getReference();

        increaseBtn = findViewById(R.id.increaseBtnId);
        decreaseBtn = findViewById(R.id.decreaseBtnId);
        feedBtn = findViewById(R.id.feedBtnId);
        feedAmount = findViewById(R.id.feedAmountId);
        testTv = findViewById(R.id.testTvId);

        timer = new Timer();


        amount = parseInt((String) feedAmount.getText());

        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               amount++;
               if (amount>=10)
                   amount = 10;
               feedAmount.setText(String.valueOf(amount));
            }
        });

        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    amount--;
                    if (amount<=0)
                        amount = 0;
                    feedAmount.setText(String.valueOf(amount));

            }
        });


        feedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   testTv.setText(String.valueOf(amount));




                AlertDialog.Builder builder = new AlertDialog.Builder(Feeding.this);

                builder.setMessage("Are you sure?").setCancelable(false).

                        setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                flag = 1;

                                dref.child("Feeding").setValue(flag);
                                Toast.makeText(Feeding.this, "please wait for "+amount+"sec", Toast.LENGTH_LONG).show();

                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        flag = 0;
                                        dref.child("Feeding").setValue(flag);
                                    }

                                },(amount*1000));


//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//                                        Toast.makeText(Feeding.this, "Feeding Done! ", Toast.LENGTH_SHORT).show();
//
//                                    }
//                                },(amount*1000)+ 1000);


                                dref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int flagValue = parseInt(snapshot.child("Feeding").getValue().toString()) ;
                                        if(flagValue == 0){
                                            Toast.makeText(Feeding.this, "Feeding Done! ", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(Feeding.this, "Oh no! connection lost -_-", Toast.LENGTH_SHORT).show();
                                    }
                                });






                            }
                        })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }
        });

    }
}