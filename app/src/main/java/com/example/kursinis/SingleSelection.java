package com.example.kursinis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.kursinis.MyAdapter;
import com.example.kursinis.R;
import com.example.kursinis.SingleAdapter;
import com.example.kursinis.produktas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SingleSelection extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    ArrayList<Item> items = new ArrayList<>();
    SingleAdapter adapter;
    Button select_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_selection);

        recyclerView = findViewById(R.id.RecyclerView);
        select_btn=findViewById(R.id.select_btn);
        database = FirebaseDatabase.getInstance().getReference("data");
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));



        items = new ArrayList<>();

        adapter = new SingleAdapter(this,items);
        recyclerView.setAdapter(adapter);

        CreateList();
        select_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (adapter.getSelected()!=null){

                    ShowToast(adapter.getSelected().getMaisto_prod());


                }else
                    ShowToast("Niekas nepasirinkta");

            }
        });


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Item produktas = dataSnapshot.getValue(Item.class);
                    items.add(produktas);
                }

                adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void CreateList() {


    }

    private void ShowToast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

}
