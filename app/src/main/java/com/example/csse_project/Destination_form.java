package com.example.csse_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Destination_form extends AppCompatActivity {
    //Firebase Variables
    FirebaseUser user;
    FirebaseDatabase rootNode;
    DatabaseReference reference1;

    //final amount variable
    float result;

    //XML component initiate
    Spinner spinner_start,spinner_end, spinner_qty;
    String bal,end_des,dist,quant,st_des, fees;
    Boolean status=false;
    String[] locations = {"Select Start","Colombo","Galle"};
    String[] locations2 = {"Select End","Jaffna","Malabe"};
    Integer[] qtyStrng= {0,1,2,3,4,5,6,7,8,9,10};
    float oldBalance;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_form);

        //Hide title bar
        getSupportActionBar().hide();
        //Hide title bar

        //Get user Token ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        TextView tokenId = findViewById(R.id.txt_tokenID);
        TextView balance = findViewById(R.id.txt_balance);
        TextView distance = findViewById(R.id.txt_dist);
        TextView fee = findViewById(R.id.txt_fee);
        spinner_start = findViewById(R.id.spinner3);
        spinner_end = findViewById(R.id.spinner4);
        spinner_qty = findViewById(R.id.spinner5);

        Button send = findViewById(R.id.btn_send);


       Data_Access_Class dac = new Data_Access_Class();

        //Set the token ID to text view
        tokenId.setText(uid);




        //Declare start spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, locations);
        spinner_start.setAdapter(dataAdapter);


        //Declare end spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, locations2);
        spinner_end.setAdapter(dataAdapter1);

        //Declare qty spinner
        ArrayAdapter<Integer> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, qtyStrng);
        spinner_qty.setAdapter(dataAdapter2);


        //Calling functions
        end_des = spinner_end.getSelectedItem().toString();
        quant = spinner_qty.getSelectedItem().toString();

        st_des = spinner_start.getSelectedItem().toString();

        getCurrentBalance(uid,balance);






        //Start destination Spinner On change
        spinner_start.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                st_des = spinner_start.getSelectedItem().toString();

                if(st_des=="Colombo" && end_des=="Malabe"){
                    distance.setText("23 km");
                    dist = "23";
                }

                else if(st_des=="Colombo" && end_des=="Jaffna"){
                    distance.setText("304 km");
                    dist = "304";
                }

                else if(st_des=="Galle" && end_des=="Jaffna"){
                    distance.setText("495 km");
                    dist = "495";
                }
                else if(st_des=="Galle" && end_des=="Malabe"){
                    distance.setText("117 km");
                    dist = "117";
                }
                else {
                    Toast.makeText(Destination_form.this, "Select Input Fields", Toast.LENGTH_SHORT).show();
                    dist="0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //End destination Spinner On change
        spinner_end.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                end_des = spinner_end.getSelectedItem().toString();
                if(st_des=="Colombo" && end_des=="Malabe"){
                    distance.setText("23 km");
                    dist = "23";
                }

                else if(st_des=="Colombo" && end_des=="Jaffna"){
                    distance.setText("304 km");
                    dist = "304";
                }

                else if(st_des=="Galle" && end_des=="Jaffna"){
                    distance.setText("495 km");
                    dist = "495";
                }
                else if(st_des=="Galle" && end_des=="Malabe"){
                    distance.setText("117 km");
                    dist = "117";
                }

                else{
                    Toast.makeText(Destination_form.this, "Select Input Fields", Toast.LENGTH_SHORT).show();
                    dist = "0";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //Quantity Spinner On change
        spinner_qty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quant = spinner_qty.getSelectedItem().toString();
                result = Float.parseFloat("1.20") * Float.parseFloat(quant) * Float.parseFloat(dist);
                if(result==0) {
                    fee.setText("0");
                    fees = "0";
                }
                else{
                    DecimalFormat twoPlaces = new DecimalFormat("0.00");
                    fee.setText(twoPlaces.format(result));
                    fees = Float.toString(result);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        builder = new AlertDialog.Builder(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(end_des!="Select End" && st_des !="Select Start" && fees!="0") {

                    Toast.makeText(getApplicationContext(), "Confirmation Message!", Toast.LENGTH_SHORT).show();
                    builder.setTitle("Alert!!")
                            .setMessage("Confirm to Buy the ticket")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    String tokId = tokenId.getText().toString();
                                    String fees = fee.getText().toString();
                                    bal = balance.getText().toString();

                                    String date = getCurrentDate() + " " + getCurrentTime();


                                    Trip_Class trip = new Trip_Class(tokId, st_des, end_des, dist, quant, fees, date);

                                    dac.add(trip).addOnSuccessListener(suc -> {
                                        Toast.makeText(getApplicationContext(), "Recorded", Toast.LENGTH_SHORT).show();

                                        updateBalance(uid);

                                        Intent intent = new Intent(Destination_form.this, QR_Show.class);
                                        intent.putExtra("bal", bal);
                                        intent.putExtra("start", st_des);
                                        intent.putExtra("end", end_des);
                                        intent.putExtra("dist", dist);
                                        intent.putExtra("quant", quant);
                                        intent.putExtra("date", date);
                                        startActivity(intent);


                                    }).addOnFailureListener(er -> {
                                        Toast.makeText(getApplicationContext(), "Fail to record" + er.getMessage(), Toast.LENGTH_SHORT).show();
                                    });


                                }
                            })


                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();

                }
                else{
                    Toast.makeText(Destination_form.this, "Input all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




    private String getCurrentTime(){
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }
    private String getCurrentDate(){
        return new SimpleDateFormat("dd LLL, yyyy", Locale.getDefault()).format(new Date());
    }




    public void getCurrentBalance (String uids, TextView balance){

        Query getData = rootNode.getInstance().getReference("Users").orderByChild("tokenid").equalTo(uids);

        getData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    oldBalance  = snapshot.child(uids).child("balance").getValue(float.class);
                    DecimalFormat twoPlaces = new DecimalFormat("0.00");
                    balance.setText(twoPlaces.format(oldBalance));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void updateBalance(String uid){
        float bal = oldBalance - result;
        reference1 = rootNode.getInstance().getReference("Users");
        reference1.child(uid).child("balance").setValue(bal);
    }

}