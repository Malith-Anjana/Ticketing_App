package com.example.csse_project;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Data_Access_Class {

    private DatabaseReference databaseReference;

    public Data_Access_Class(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Trip_Class.class.getSimpleName());
    }

    public Task<Void> add(Trip_Class trip){
        return databaseReference.push().setValue(trip);
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    public Query get(){
        return databaseReference.orderByKey();
    }
}
