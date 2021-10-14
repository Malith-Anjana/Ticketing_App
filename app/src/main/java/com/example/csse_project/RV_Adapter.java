package com.example.csse_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RV_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<Trip_Class> list = new ArrayList<>();
    public RV_Adapter(Context ctx){
        this.context=ctx;
    }

    public void setItems(ArrayList<Trip_Class> trip){
        list.addAll(trip);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_card, parent, false);
        return new TripVH_Class(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

       TripVH_Class vh = (TripVH_Class) holder;
       Trip_Class trip = list.get(position);
       vh.txt_start.setText(trip.getStart());
       vh.txt_end.setText(trip.getEnd());
       vh.txt_qty.setText(trip.getQty());
       vh.txt_dist.setText(trip.getDistance());
       vh.txt_date.setText(trip.getDate());

      vh.card_btn.setOnClickListener(view -> {
          Intent intent = new Intent(context, QR_Show.class);
          intent.putExtra("start", trip.getStart());
          intent.putExtra("end", trip.getEnd());
          intent.putExtra("dist", trip.getDistance());
          intent.putExtra("quant", trip.getQty());
          intent.putExtra("date", trip.getDate());
          context.startActivity(intent);
      });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
