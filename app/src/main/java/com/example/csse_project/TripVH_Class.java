package com.example.csse_project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TripVH_Class extends RecyclerView.ViewHolder {
    public TextView txt_tok, txt_start,txt_end,txt_qty,txt_date,txt_dist;
    public CardView card_btn;

    public TripVH_Class(@NonNull View itemView) {
        super(itemView);

        txt_start = itemView.findViewById(R.id.textVi2);
        txt_end = itemView.findViewById(R.id.textVi4);
        txt_qty = itemView.findViewById(R.id.textVi8);
        txt_dist = itemView.findViewById(R.id.textVi10);
        txt_date = itemView.findViewById(R.id.textVi6);
        card_btn = itemView.findViewById(R.id.trip_card);
    }
}
