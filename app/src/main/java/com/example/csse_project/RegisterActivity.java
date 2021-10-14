package com.example.csse_project;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    //Variables
    EditText regName, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        regName = findViewById(R.id.reg_name);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                String name = regName.getText().toString();

                String email = regEmail.getText().toString();
                String phoneNo = regPhoneNo.getText().toString();
                String password = regPassword.getText().toString();

                if(email.isEmpty()){
                    regEmail.setError("Please enter the email id");
                    regEmail.requestFocus();
                }
                else if(name.isEmpty()){
                    regPassword.setError("Please enter the name");
                    regPassword.requestFocus();
                }else if(password.isEmpty()){
                    regName.setError("Please enter the password ");
                    regName.requestFocus();
                }else if(phoneNo.isEmpty()){
                    regPhoneNo.setError("Please enter the phone number ");
                    regPhoneNo.requestFocus();
                }



                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            String tokenid = mAuth.getUid();
                            float amount =0;
                            UserHelper helperClass = new UserHelper(name, tokenid, email, phoneNo,amount);

                            reference.child(tokenid).setValue(helperClass);

                            Toast.makeText(RegisterActivity.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),PaymentActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Registered Unsuccessful",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });


        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}