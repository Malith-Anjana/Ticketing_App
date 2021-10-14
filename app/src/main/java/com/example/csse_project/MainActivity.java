package com.example.csse_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    EditText ed1,ed2;
    TextView reg;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btnlng);
        ed1 = findViewById(R.id.mail);
        ed2 = findViewById(R.id.password);
        reg = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();

                if(mFirebaseUser != null){
                    Toast.makeText(MainActivity.this,"You are logged in",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this,PaymentActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"Please Login",Toast.LENGTH_LONG).show();

                }

            }
        };


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");



                String email = ed1.getText().toString();
                String password = ed2.getText().toString();

                if(email.isEmpty()){
                    ed1.setError("Please enter the email id");
                    ed1.requestFocus();
                }
                else if(password.isEmpty()){
                    ed2.setError("Please enter the password ");
                    ed2.requestFocus();
                }else if(!(email.isEmpty() && password.isEmpty())) {

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Login Error, Please Login again", Toast.LENGTH_LONG).show();
                            } else{
                                Intent i = new Intent(MainActivity.this,PaymentActivity.class);
                                startActivity(i);
                            }
                        }

                    });

                } else {
                        Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_LONG).show();

                        }
                        }
                    });
                }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}



