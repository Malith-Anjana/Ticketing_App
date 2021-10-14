package com.example.csse_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PaymentActivity extends AppCompatActivity {


    TextView tid,balance;
    EditText amount;
    RadioGroup pay;
    RadioButton payMethod1,payMethod2,payMethod3;
    Button btn;
    DrawerLayout d1;
    ActionBarDrawerToggle abdt;

    private FirebaseAuth mAuth;
    NavigationView nv;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;
    DatabaseReference reference;
    FirebaseDatabase rootNode;


    float newbalance;
    float oldBalance;
    float enterAmount;
    String ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);



        amount = findViewById(R.id.etxtAmount);
        pay = findViewById(R.id.rg);
        d1 = findViewById(R.id.d1);
        nv = findViewById(R.id.nav_view);
        btn = findViewById(R.id.btnpay);
        tid = findViewById(R.id.tokenidtxt);
        balance = findViewById(R.id.balancetxt);
        payMethod1 = findViewById(R.id.radiocard);
        payMethod2 = findViewById(R.id.radiopaypal);


        user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        tid.setText(uid);
        abdt = new ActionBarDrawerToggle(this,d1,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        d1.addDrawerListener(abdt);
        abdt.syncState();




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.credit){
//                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    Toast.makeText(PaymentActivity.this, "Credit", Toast.LENGTH_LONG).show();

                }
                else if( id == R.id.trip){
                    Toast.makeText(PaymentActivity.this, "trip", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PaymentActivity.this, Destination_form.class);
                    startActivity(intent);

                }

                else if( id == R.id.mytickets){
                    Toast.makeText(PaymentActivity.this, "My tickets", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PaymentActivity.this, Trip_List_Activity.class);
                    startActivity(intent);

                }

                else if(id == R.id.logout){
                    FirebaseAuth.getInstance().signOut();;
                    Intent i = new Intent(PaymentActivity.this,MainActivity.class);
                    startActivity(i);
                }

                return true;
            }
        });


        getData(uid);
//        ob = String.valueOf(oldBalance);
//        balance.setText(ob);


//        Toast.makeText(PaymentActivity.this, (int) oldBalance, Toast.LENGTH_LONG).show();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = pay.getCheckedRadioButtonId();
                payMethod3 = findViewById(radioId);
                enterAmount = Float.valueOf(amount.getText().toString());
                newbalance = oldBalance + enterAmount;

                if(payMethod3 == payMethod1){
                    Intent i =new Intent(PaymentActivity.this,CreditcardActivity.class);
                    i.putExtra("ti",uid);
                    i.putExtra("enteramount",enterAmount);
                    i.putExtra("amount",newbalance);
                    startActivity(i);
                }
            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(abdt.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void getData (String uid){

        Query getData = rootNode.getInstance().getReference("Users").orderByChild("tokenid").equalTo(uid);

        getData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                oldBalance  = snapshot.child(uid).child("balance").getValue(float.class);
                ob = String.valueOf(oldBalance);
                balance.setText(ob);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}