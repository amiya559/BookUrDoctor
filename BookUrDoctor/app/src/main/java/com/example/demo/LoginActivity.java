package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText loginemail, loginpswrd;
    Button btn_login;
    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginemail = (EditText) findViewById(R.id.log_in_email);
        loginpswrd = (EditText) findViewById(R.id.log_in_pswrd);
        btn_login = (Button) findViewById(R.id.buttonLogin);

        progressBar = (ProgressBar) findViewById(R.id.loginProgressbar);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String stringEmail = loginemail.getText().toString();
                String stringPswrd = loginpswrd.getText().toString();


                if (TextUtils.isEmpty(stringEmail)){
                    Toast.makeText(LoginActivity.this, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
                }


                if (TextUtils.isEmpty(stringPswrd)){
                    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);


                firebaseAuth.signInWithEmailAndPassword(stringEmail, stringPswrd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(), NavigationDrawerActivity.class));
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(LoginActivity.this, "Invalid Email/Password or Check Your Internet", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


            }
        });


        //Intent intent=new Intent(LoginActivity.this,NavigationDrawerActivity.class);
        //startActivity(intent);


    /*
    public void facebookbtn(View view){

    }

    public void gmailbtn(View view) {
    }

    public void forgot_pswrd_btn(View view) {
    }
    */

    }
}
