package com.example.csse_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Trip_List_Activity extends AppCompatActivity {
    SwipeRefreshLayout sfl;
    RecyclerView rcv;
    RV_Adapter adapter;
   Data_Access_Class dac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        //Hide title bar
        getSupportActionBar().hide();
        //Hide title bar

        sfl = findViewById(R.id.swap);
        RecyclerView rcv = findViewById(R.id.rv);
        rcv.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcv.setLayoutManager(manager);
        adapter = new RV_Adapter(this);

        rcv.setAdapter(adapter);
        dac = new Data_Access_Class();

        loadData();
    }

    private void loadData(){
        dac.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Trip_Class> trips = new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren()){
                    Trip_Class trip = data.getValue(Trip_Class.class);
                    trips.add(trip);
                }
                adapter.setItems(trips);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}