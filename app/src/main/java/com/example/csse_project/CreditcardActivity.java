package com.example.csse_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreditcardActivity extends AppCompatActivity {
    CardForm cardForm;
    Button buy;
    AlertDialog.Builder alertBuilder;
    TextView tv;

    FirebaseDatabase rootNode;
    DatabaseReference reference,reference1;
    FirebaseUser user;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcard);

        getSupportActionBar().hide();

        cardForm = findViewById(R.id.card_form);
        buy = findViewById(R.id.btnBuy);
        tv = findViewById(R.id.amount);

        Intent i =getIntent();
        float etramut = i.getFloatExtra("enteramount",0);
        float newbalance = i.getFloatExtra("amount",0);
        String pay = String.valueOf(etramut);

        String uid = i.getStringExtra("ti");

        tv.setText(pay);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .setup(CreditcardActivity.this);

        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid()) {
                    alertBuilder = new AlertDialog.Builder(CreditcardActivity.this);
                    alertBuilder.setTitle("Confirm before adding");
                    alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                            "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "Card CVV: " + cardForm.getCvv() + "\n" +
                            "Postal code: " + cardForm.getPostalCode() + "\n" +
                            "Phone number: " + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference("Payment");
                            user = FirebaseAuth.getInstance().getCurrentUser();

                            String cardNumber = cardForm.getCardNumber();
                            String expireDate = cardForm.getExpirationDateEditText().getText().toString();
                            String cvc = cardForm.getCvv();
                            String postalCode = cardForm.getPostalCode() ;
                            String mobile = cardForm.getMobileNumber();

                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());


                            CreditCardHelper credit = new CreditCardHelper(uid,cardNumber,cvc,postalCode,mobile,expireDate,date);
                            reference.child(uid).setValue(credit);
                            updateBalance(uid,newbalance);


                            Toast.makeText(CreditcardActivity.this, "Credit Added Successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),PaymentActivity.class));
                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();

                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("Payment");




                } else {
                    Toast.makeText(CreditcardActivity.this, "Please complete the form", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void updateBalance(String uid,float balance){
        reference1 = rootNode.getInstance().getReference("Users");
        reference1.child(uid).child("balance").setValue(balance);
    }




}