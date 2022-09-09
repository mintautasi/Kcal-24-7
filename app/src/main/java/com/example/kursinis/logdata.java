package com.example.kursinis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class logdata extends AppCompatActivity {

    RecyclerView recyclerViewAdd;
    DatabaseReference database;
    ArrayList<produktas> listSmall;
    MyAdapterSmall myAdapterSmall;
    Button dataAdd,dataReg;
    LinearLayout dataSum;
    TextView prodSum1,prodSum2,prodSum3,prodSum4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logdata);

        dataAdd=findViewById(R.id.add_data_btn);
        recyclerViewAdd = findViewById(R.id.RecyclerViewAdd);
        dataReg = findViewById(R.id.reg_data_btn);
        dataSum = findViewById(R.id.data_sum);
        prodSum1 = findViewById(R.id.prod_sum1);
        prodSum2 = findViewById(R.id.prod_sum2);
        prodSum3 = findViewById(R.id.prod_sum3);
        prodSum4 = findViewById(R.id.prod_sum4);
        database = FirebaseDatabase.getInstance().getReference("data");
        recyclerViewAdd.setHasFixedSize(true);
        recyclerViewAdd.setLayoutManager(new LinearLayoutManager(this));

        dataAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),SingleSelection.class));
            }
        });

        listSmall = new ArrayList<>();

        myAdapterSmall = new MyAdapterSmall(this,listSmall);
        recyclerViewAdd.setAdapter(myAdapterSmall);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    produktas produktas = dataSnapshot.getValue(produktas.class);
                    listSmall.add(produktas);
                }

                myAdapterSmall.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





}


